package com.luv2code.springboot.thymeleafdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DemoLoggingAspect {
    private Logger myLogger = Logger.getLogger(getClass().getName());

    // match everything in controller package
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
    private void forControllerPackage() {}

    // match everything in service package
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
    private void forServicePackage() {}

    // match everything in DAO package
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
    private void forDaoPackage() {}

    // matches all three packages
    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()") 
    private void forAppFlow() {}

    @Before("forAppFlow()")
    public void before(JoinPoint jp) {
        // display the method
        String method = jp.getSignature().toShortString();
        this.myLogger.info("=====>> @Before: calling method: " + method);

        // display arguments of the method 
        Object[] args = jp.getArgs();
        for (Object arg: args) {
            this.myLogger.info("=====>> argument: " + arg);
        }
    }

    @AfterReturning(
        pointcut = "forAppFlow()",
        returning = "result")
    public void afterReturning(JoinPoint jp, Object result) {

        // display method 
        String method = jp.getSignature().toShortString();
        myLogger.info("=====>> in @AfterReturning: from method: " + method);

        // display data returned
        myLogger.info("=====>> result: " + result);

    }
}
