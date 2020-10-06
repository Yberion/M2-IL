package fr.brandon.tp3.part1_2.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Security
{
    @Around("execution(* fr.brandon.tp3.part1_2.bank.implementation.*.*(..))")
    public void secureTransaction(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
    {
        System.out.println(
                "SECURITY: Open security manager for the methode " + proceedingJoinPoint.getSignature().getName());
        proceedingJoinPoint.proceed();
        System.out
                .println("SECURITY: Close security manager on methode " + proceedingJoinPoint.getSignature().getName());
    }
}
