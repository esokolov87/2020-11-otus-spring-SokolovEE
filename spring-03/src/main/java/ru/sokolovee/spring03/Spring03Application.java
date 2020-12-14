package ru.sokolovee.spring03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.sokolovee.spring03.config.AppProps;
import ru.sokolovee.spring03.service.TestService;
import ru.sokolovee.spring03.service.TestServiceImpl;

@SpringBootApplication
@EnableConfigurationProperties(AppProps.class)
public class Spring03Application {

	@Autowired
	TestService testService;

	public static void main(String[] args) {
		var context = SpringApplication.run(Spring03Application.class, args);

		var testService = context.getBean(TestServiceImpl.class);
		testService.startTest();
	}

}
