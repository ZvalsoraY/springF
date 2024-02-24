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

    @After("@annotation(Logging)")
    public void loggingExecuteMethod(JoinPoint joinPoint) {
        myLogger.info("Next method done: " + joinPoint.getSignature().toShortString());
    }
}
