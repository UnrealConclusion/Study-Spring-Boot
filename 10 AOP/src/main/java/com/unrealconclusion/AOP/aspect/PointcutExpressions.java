package com.unrealconclusion.AOP.aspect;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
public class PointcutExpressions {
    // matches any method in the package
    @Pointcut("execution(* com.unrealconclusion.AOP.dao.*.*(..))")
    public void thisPackage() {}

    // matches any getter method
    @Pointcut("execution(* com.unrealconclusion.AOP.dao.*.get*(..))")
    public void getter() {}

    // matches any setter method
    @Pointcut("execution(* com.unrealconclusion.AOP.dao.*.set*(..))")
    public void setter() {}

    // matches any method in the package, but excludes getter and setter methods 
    @Pointcut("thisPackage() && !(getter() || setter())")
    public void thisPackageExcludeGetterSetter() {}
}
