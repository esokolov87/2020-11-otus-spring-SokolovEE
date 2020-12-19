package ru.sokolovee.spring04.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

@Configuration
public class ReaderConfig {

    private final AppProps props;
    private final Environment env;

    public ReaderConfig(AppProps props, Environment env) {
        this.props = props;
        this.env = env;
    }

    @Bean
    public BufferedReader fileReader() {
        String fileName = env.getProperty("application.fileUrl." + props.getLocale().toString());
        return new BufferedReader(new InputStreamReader(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(fileName))));
    }


    @Bean
    public BufferedReader consoleReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
