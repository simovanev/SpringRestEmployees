package com.spring.cruddemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoginAspect {
    private final Logger logger=Logger.getLogger(getClass().getName());
    @Pointcut("execution(* com.spring.cruddemo.controller.*.*(..))")
    private void forControllerPackage(){}
    @Pointcut("execution(* com.spring.cruddemo.service.*.*(..))")
    private void forServicePackage(){}
    @Pointcut("execution(* com.spring.cruddemo.repository.*.*(..))")
    private void forRepositoryPackage(){}
    @Pointcut("forControllerPackage() || forRepositoryPackage() || forServicePackage()")
    private void forAppFlow(){}

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint){
        String methodName=joinPoint.getSignature().getName();
        logger.info("++++++========Before calling method "+methodName);
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            logger.info("========="+arg);
        }
    }
    @AfterReturning(pointcut = "forAppFlow()",returning = "theObject")
    public void afterReturning(JoinPoint joinPoint, Object theObject){
        String methodName=joinPoint.getSignature().getName();
        logger.info("++++++++++++After calling method "+methodName);
        logger.info("theObject="+theObject);
    }
}
