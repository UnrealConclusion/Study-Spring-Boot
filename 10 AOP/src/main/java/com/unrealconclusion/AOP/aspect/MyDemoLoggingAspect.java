package com.unrealconclusion.AOP.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    // matches any method in the package
    @Pointcut("execution(* com.unrealconclusion.AOP.dao.*.*(..))")
    private void thisPackage() {}

    // matches any getter method
    @Pointcut("execution(* com.unrealconclusion.AOP.dao.*.get*(..))")
    private void getter() {}

    // matches any setter method
    @Pointcut("execution(* com.unrealconclusion.AOP.dao.*.set*(..))")
    private void setter() {}

    // matches any method in the package, but excludes getter and setter methods 
    @Pointcut("thisPackage() && !(getter() || setter())")
    private void thisPackageExcludeGetterSetter() {}

    @Before("thisPackageExcludeGetterSetter()")
    public void beforeAddAccountAdvice() {
        System.out.println("\n=====>>> Executing @Before advice on method");
    }

    @Before("thisPackageExcludeGetterSetter()")
    public void performApiAnalytics() {
         System.out.println("\n=====>>> Performing API Analytics");
    }
}
