package ru.sokolovee.spring15;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.sokolovee.spring15.domain.Bet;
import ru.sokolovee.spring15.domain.Check;

import java.util.Collection;

@MessagingGateway
public interface Bookmaker {

    @Gateway(requestChannel = "betsChannel", replyChannel = "checkChannel")
    Collection<Check> process(Collection<Bet> bets);
}
