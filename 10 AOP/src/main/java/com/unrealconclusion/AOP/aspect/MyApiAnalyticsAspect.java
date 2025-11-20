package com.unrealconclusion.AOP.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(3)
@Component
public class MyApiAnalyticsAspect {
    @Before("com.unrealconclusion.AOP.aspect.PointcutExpressions.thisPackageExcludeGetterSetter()")
    public void performApiAnalytics() {
        System.out.println("\n=====>>> Performing API Analytics");
    }
}
