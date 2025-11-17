package com.unrealconclusion.AOP;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.unrealconclusion.AOP.dao.AccountDAO;

@SpringBootApplication
public class AopApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO accountDAO) {
		return runner -> {
			BeforeAdviceDemo(accountDAO);
		};
	}

	private void BeforeAdviceDemo(AccountDAO accountDAO) {
		accountDAO.addAccount(); // business method 
	}
}
