package com.unrealconclusion.AOP.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
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
}
