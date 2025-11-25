package com.unrealconclusion.AOP.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.unrealconclusion.AOP.Account;

@Aspect
@Order(2)
@Component
public class MyDemoLoggingAspect {
    @Before("com.unrealconclusion.AOP.aspect.PointcutExpressions.thisPackageExcludeGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
        System.out.println("\n=====>>> Executing @Before advice on method");

        // display the method signature 
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Method: " + methodSignature);

        // display method arguments 
        Object[] args = joinPoint.getArgs(); // get arguments 
        for (Object arg: args) {
            System.out.println(arg);
            if (arg instanceof Account) {
                Account account = (Account) arg;
                System.out.println("Account name: " + account.getName());
                System.out.println("Account level: " + account.getLevel());
            }
        } 
    }

    @AfterReturning(
        pointcut = "execution(* com.unrealconclusion.AOP.dao.AccountDAO.findAccounts(..))",
        returning = "result" // must match parameter 
    )
    public void afterReturningFindAccountAdvice(JoinPoint joinPoint, List<Account> result) {
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterReturning on method: " + method);
        System.out.println("\\n=====>>> Results: " + result);

        // modify all the names to be uppercase 
        for (Account account: result) {
            account.setName(account.getName().toUpperCase());
        }
    }

    @AfterThrowing(
        pointcut = "execution(* com.unrealconclusion.AOP.dao.AccountDAO.findAccounts(..))",
        throwing = "exception"  // must match parameter
    )
    public void AfterThrowingFindAccountAdvice(JoinPoint joinPoint, Throwable exception) {
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterThrowing on method: " + method);

        // log exception
        System.out.println("\n=====>>> The exception is: " + exception);
    }

    @After(("execution(* com.unrealconclusion.AOP.dao.AccountDAO.findAccounts(..))"))
    public void afterFindAccountAdvice(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @After on method: " + method);
    }

    @Around("execution(* com.unrealconclusion.AOP.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        String method = proceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @Around on method: " + method);

        // get starting timestamp 
        long start = System.nanoTime();

        // execute the method 
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            System.out.println(e.getMessage());

            // return this messege instead of a fortune
            result = "You got blowned up!"; 
        } 

        // get the ending timestamp 
        long end = System.nanoTime();

        // compute duriation and print it out
        long duration = end - start;
        System.out.println("\n=====>>> Duration: " + duration / 1000.0 + " nano seconds");

        return result; // return the result of the call 
    }
}
