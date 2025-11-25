package com.unrealconclusion.AOP;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.unrealconclusion.AOP.dao.AccountDAO;
import com.unrealconclusion.AOP.dao.MembershipDAO;
import com.unrealconclusion.AOP.service.TrafficFortuneService;

@SpringBootApplication
public class AopApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO accountDAO, MembershipDAO membershipDAO, TrafficFortuneService trafficFortuneService) {
		return runner -> {
			// BeforeAdviceDemo(accountDAO, membershipDAO);
			// AfterReturningAdviceDemo(accountDAO);
			// AfterThrowingAdviceDemo(accountDAO);
			// AfterAdviceDemo(accountDAO);
			// AroundAdviceDemo(trafficFortuneService);
			// AroundAdviceWithExceptionDemo(trafficFortuneService);
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

	private void AfterReturningAdviceDemo(AccountDAO accountDAO) {
		List<Account> accounts = accountDAO.findAccounts();
		System.out.println("\n\nMain Program: Demoing the After Advice");
		System.out.println("----");
		System.out.println(accounts);
		System.out.println("\n");
	}

	private void AfterThrowingAdviceDemo(AccountDAO accountDAO) {
		List<Account> accounts = null; 

		try {
			boolean tripWire = true; // throws an exception when true 
			accounts = accountDAO.findAccounts(tripWire);
		} catch (Exception e) {
			System.out.println("\n\n Main Program: ... caught exception: " + e);
		}
		System.out.println("\n\nMain Program: Demoing the After Throwing Advice");
		System.out.println("----");
		System.out.println(accounts);
		System.out.println("\n");
	}

	private void AfterAdviceDemo(AccountDAO accountDAO) {
		List<Account> accounts = null; 

		try {
			boolean tripWire = false; // After advice will be called regard less of success 
			accounts = accountDAO.findAccounts(tripWire); 
		} catch (Exception e) {
			System.out.println("\n\n Main Program: ... caught exception: " + e);
		}
		System.out.println("\n\nMain Program: Demoing the After Advice");
		System.out.println("----");
		System.out.println(accounts);
		System.out.println("\n");
	}

	private void AroundAdviceDemo(TrafficFortuneService trafficFortuneService) {
		System.out.println("\nMain Program: Demoing the Around Advice");
		System.out.println("Calling getFortune()");
		String data = trafficFortuneService.getFortune();
		System.out.println("\nMy fortune is: " + data);
	}

	private void AroundAdviceWithExceptionDemo(TrafficFortuneService trafficFortuneService) {
		System.out.println("\nMain Program: Demoing the Around Advic with an Exception");
		System.out.println("Calling getFortune()");
		boolean tripWire = true;
		String data = trafficFortuneService.getFortune(tripWire);
		System.out.println("\nMy fortune is: " + data);
	}
}
