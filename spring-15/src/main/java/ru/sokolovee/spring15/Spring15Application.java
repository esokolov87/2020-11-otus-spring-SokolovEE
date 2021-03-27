package ru.sokolovee.spring15;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.sokolovee.spring15.domain.Bet;
import ru.sokolovee.spring15.domain.Check;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

@ComponentScan
@IntegrationComponentScan
@Configuration
@EnableIntegration
public class Spring15Application {

    private static final String[] SPORT = {"футбол", "баскетбол", "бокс", "гонки", "шахматы", "бег", "плавание", "биатлон"};

    @Bean
    public QueueChannel betsChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public PublishSubscribeChannel checkChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2).get();
    }

    @Bean
    public IntegrationFlow bookmakerFlow() {
        return IntegrationFlows.from("betsChannel")
                .split()
                .handle("bookmakerService", "set")
                .aggregate()
                .channel("checkChannel")
                .get();
    }

    public static void main(String[] args) {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(Spring15Application.class);

        Bookmaker bookmaker = ctx.getBean(Bookmaker.class);

        ForkJoinPool pool = ForkJoinPool.commonPool();

        while (true) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

            pool.execute(() -> {
                Collection<Bet> bets = generateBets();
                System.out.println("Новые ставки: " +
                        bets.stream().map(Bet::getName)
                                .collect(Collectors.joining(",")));
                Collection<Check> check = bookmaker.process(bets);
                System.out.println("Готовы результаты: " + check.stream()
                        .map(Check::getName)
                        .collect(Collectors.joining(",")));
            });
        }
    }

    private static Bet generateBet() {
        return new Bet(SPORT[RandomUtils.nextInt(0, SPORT.length)], RandomUtils.nextLong(100, 1000), RandomUtils.nextLong(1, 10));
    }

    private static Collection<Bet> generateBets() {
        List<Bet> bets = new ArrayList<>();
        for (int i = 0; i < RandomUtils.nextInt(1, 5); i++) {
            bets.add(generateBet());
        }
        return bets;
    }
}
