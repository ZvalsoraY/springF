package ru.ok.spring.logger;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggerUtilJava {
    private final Logger myLogger = Logger.getAnonymousLogger();
    @After("execution(* ru.ok.spring.controller.*.*(..))")
    public void logAfterControllerMethodCall(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        myLogger.info("Next method done: " + methodName + ", controller: " + className);
    }
}
