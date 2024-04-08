package com.nikron.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Component
@Slf4j
public class FirstAspect {

    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void isControllerLayer() {

    }

    @Pointcut("within(com.nikron.spring.service.*Service)")
    public void isServiceLayer() {

    }

    @Pointcut("this(org.springframework.stereotype.Repository)")
    //@Pointcut("target(org.springframework.stereotype.Repository)")
    public void isRepositoryLayer() {

    }

    @Pointcut("isControllerLayer() && @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void hasGetMapping() {

    }

    @Pointcut("isControllerLayer() && args(org.springframework.ui.Model,..)")
    public void hasModelArg() {
    }

    @Pointcut("isControllerLayer() && @args(com.nikron.spring.validator.UserInfo,..)")
    public void hasUserInfoParamAnnotation() {
    }

    @Pointcut("bean(userService)")
    public void isUserServiceBean() {
    }

    @Pointcut("bean(*Service)")
    public void isServiceLayerBean() {
    }

    @Pointcut("execution(public Long com.nikron.spring.service.*Service.findById(Integer, *))")
    public void anyServiceFindByIdMethod() {
    }

    @Pointcut("execution(public * findById(*))")
    public void anyFindByIdMethod() {
    }

    //Advice

    @Before("anyServiceFindByIdMethod() " +
            "&& args(id) " +
            "&& target(service) " +
            "&& this(serviceProxy) " +
            "&& @within(transactional)")
    public void addLogging(JoinPoint joinPoint,
                           Object id,
                           Object service,
                           Object serviceProxy,
                           Transactional transactional) {
        log.info("Before invoke findById method in class {}, with id {}", service, id);
    }

    @AfterReturning(value = "anyServiceFindByIdMethod()" +
                            " && target(service)",
                    returning = "result")
    public void addLoggingAfterReturning(Object result, Object service) {
        log.info("AfterReturning invoked findById method in class {}, with id {}", service, result);
    }

    @AfterThrowing(value = "anyServiceFindByIdMethod()" +
            " && target(service)",
            throwing = "ex")
    public void addLoggingAfterThrowing(Throwable ex, Object service) {
        log.info("AfterThrowing invoked findById method in class {}, with throwing {}", service, ex);
    }

    @After("anyServiceFindByIdMethod() && target(service)")
    public void addLoggingAfter(Object service){
        log.info("After invoked findById method in class {}", service);
    }



    @Around("anyServiceFindByIdMethod() && target(service) && args(id)")
    public Object addLoggingAround(ProceedingJoinPoint joinPoint, Object service, Object id) throws Throwable {
        log.info("AROUND before invoke findById method in class {}, with id {}", service, id);
        try {
            Object result = joinPoint.proceed();
            log.info("AROUND after returning - invoked findById method in class {}, with id {}", service, id);
            return result;
        } catch (Throwable ex) {
            log.info("AROUND after throwing - invoked findById method in class {}, exception {}: {}", service, ex.getCause(), ex.getMessage());
            throw ex;
        } finally {
            log.info("AROUND after(finally) - invoked findById method in class {}", service);
        }
    }
}
