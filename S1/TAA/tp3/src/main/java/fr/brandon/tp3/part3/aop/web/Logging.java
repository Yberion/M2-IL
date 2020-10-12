package fr.brandon.tp3.part3.aop.web;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class Logging
{
    @Before("within(fr.brandon.tp3.part3.web..*)")
    public void logRestAccess(JoinPoint joinPoint)
    {
        log.info(joinPoint.getSignature().getName() + " -> " + joinPoint.getTarget().getClass());
    }
}
