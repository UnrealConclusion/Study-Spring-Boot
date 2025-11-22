package com.unrealconclusion.AOP;

import java.util.List;

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
			// BeforeAdviceDemo(accountDAO, membershipDAO);
			AfterAdviceDemo(accountDAO);
		};
	}

	private void BeforeAdviceDemo(AccountDAO accountDAO, MembershipDAO membershipDAO) {
		accountDAO.addAccount(new Account("Spongebob", "Gold"), false);
		accountDAO.doWork();
		accountDAO.setName("Spongebob");
		accountDAO.setServiceCode("420");
		accountDAO.getName();
		accountDAO.getServiceCode();
		membershipDAO.addAccount();
		membershipDAO.doNothing();
	}

	private void AfterAdviceDemo(AccountDAO accountDAO) {
		List<Account> accounts = accountDAO.findAccounts();
		System.out.println("\n\nMain Program: Demoing the After Advice");
		System.out.println("----");
		System.out.println(accounts);
		System.out.println("\n");
	}
}
