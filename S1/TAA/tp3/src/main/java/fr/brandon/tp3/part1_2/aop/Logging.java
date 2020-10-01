package fr.brandon.tp3.part1_2.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logging
{
    @Before("execution(* fr.brandon.tp3.part1_2.*.implementation.*.*(..))")
    public void logBeforeCall(JoinPoint joinPoint)
    {
        System.out.println("LOGGING: Calling methode \"" + joinPoint.getSignature().getName() + "()\" from the class \"" + joinPoint.getTarget().getClass() + "\"");
    }
}
