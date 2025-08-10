package com.djm.ecom.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Match all classes in com.djm.ecom.service package
    @Pointcut("execution(* com.djm.ecom.service..*(..))")
    public void serviceLayer(){}

    // Match all classes in com.djm.ecom.controller package
    @Pointcut("execution(* com.djm.ecom.controller..*(..))")
    public void controllerLayer(){}

    // Before method execution of controller layer methods
    @Before("controllerLayer()")
    public void logBeforeController(JoinPoint joinPoint)
    {
        logger.info("Controller call: {}.{}() with arguments ={}",
                joinPoint.getSignature().getDeclaringType(),
                joinPoint.getSignature().getName(),
                joinPoint.getArgs());
    }

    //Before method execution of service layer methods.
    @Before(("serviceLayer()"))
    public void logBeforeService(JoinPoint joinPoint)
    {
        logger.info("Service call: {}.{}() with arguments = {}",
                joinPoint.getSignature().getDeclaringType(),
                joinPoint.getSignature().getName(),
                joinPoint.getArgs());
    }

    //After Returning (Controller and Services)
    @AfterReturning(pointcut = "controllerLayer() || serviceLayer()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result)
    {
        logger.info("Method {}.{}() executed successfully. Return ={}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                result);
    }

    //After throwing exception(Controller and Services)
    @AfterThrowing(pointcut = "controllerLayer() || serviceLayer()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex)
    {
        logger.error("Exception in {}.{}() with case = {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                ex.getMessage(),ex);
    }


}
