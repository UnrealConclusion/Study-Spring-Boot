package com.unrealconclusion.AOP;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.unrealconclusion.AOP.dao.AccountDAO;
import com.unrealconclusion.AOP.dao.MembershipDAO;

@SpringBootApplication
public class AopApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO accountDAO, MembershipDAO membershipDAO) {
		return runner -> {
			BeforeAdviceDemo(accountDAO, membershipDAO);
		};
	}

	private void BeforeAdviceDemo(AccountDAO accountDAO, MembershipDAO membershipDAO) {
		accountDAO.addAccount(new Account(), false);
		accountDAO.doWork();
		membershipDAO.addAccount();
		membershipDAO.doNothing();
	}
}
