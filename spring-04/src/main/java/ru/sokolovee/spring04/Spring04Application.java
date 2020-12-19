package ru.sokolovee.spring04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import ru.sokolovee.spring04.config.AppProps;
import ru.sokolovee.spring04.service.TestServiceImpl;

@SpringBootApplication
@EnableConfigurationProperties(AppProps.class)
public class Spring04Application {

    public ApplicationEventMulticaster applicationEventMulticaster() {
        SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
        eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return eventMulticaster;
    }

    public static void main(String[] args) {
        SpringApplication.run(Spring04Application.class, args);
    }
}

