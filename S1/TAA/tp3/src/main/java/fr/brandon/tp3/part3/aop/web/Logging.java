package fr.brandon.tp3.part3.aop.web;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class Logging
{
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping) || "
            + "@annotation(org.springframework.web.bind.annotation.PostMapping) || "
            + "@annotation(org.springframework.web.bind.annotation.PutMapping) || "
            + "@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void catchMapping()
    {
        // Do nothing because it's a pointcut.
    }

    @Before("within(fr.brandon.tp3.part3.web..*) && catchMapping()")
    public void logRestAccess(JoinPoint joinPoint)
    {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        RequestMapping classRequestMapping = joinPoint.getTarget().getClass().getAnnotation(RequestMapping.class);
        Annotation[] methodMapping = method.getAnnotations();
        String methodRequestMappingStr = "";
        String classRequestMappingStr = "";
        String methodTypeStr = "";

        // Could be done better
        if (classRequestMapping.path().length > 0)
        {
            classRequestMappingStr = classRequestMapping.path()[0];
        }
        else if (classRequestMapping.value().length > 0)
        {
            classRequestMappingStr = classRequestMapping.value()[0];
        }

        // Could be done better
        for (Annotation annotation : methodMapping)
        {

            switch (annotation.annotationType().getSimpleName())
            {
                case "GetMapping":
                {

                    if (method.getAnnotation(GetMapping.class).path().length > 0)
                    {
                        methodRequestMappingStr = method.getAnnotation(GetMapping.class).path()[0];
                    }
                    else if (method.getAnnotation(GetMapping.class).value().length > 0)
                    {
                        methodRequestMappingStr = method.getAnnotation(GetMapping.class).value()[0];
                    }
                    methodTypeStr = "GET";
                    break;
                }
                case "PostMapping":
                {

                    if (method.getAnnotation(PostMapping.class).path().length > 0)
                    {
                        methodRequestMappingStr = method.getAnnotation(PostMapping.class).path()[0];
                    }
                    else if (method.getAnnotation(PostMapping.class).value().length > 0)
                    {
                        methodRequestMappingStr = method.getAnnotation(PostMapping.class).value()[0];
                    }
                    methodTypeStr = "POST";
                    break;
                }
                case "PutMapping":
                {

                    if (method.getAnnotation(PutMapping.class).path().length > 0)
                    {
                        methodRequestMappingStr = method.getAnnotation(PutMapping.class).path()[0];
                    }
                    else if (method.getAnnotation(PutMapping.class).value().length > 0)
                    {
                        methodRequestMappingStr = method.getAnnotation(PutMapping.class).value()[0];
                    }
                    methodTypeStr = "PUT";
                    break;
                }
                case "DeleteMapping":
                {

                    if (method.getAnnotation(DeleteMapping.class).path().length > 0)
                    {
                        methodRequestMappingStr = method.getAnnotation(DeleteMapping.class).path()[0];
                    }
                    else if (method.getAnnotation(DeleteMapping.class).value().length > 0)
                    {
                        methodRequestMappingStr = method.getAnnotation(DeleteMapping.class).value()[0];
                    }
                    methodTypeStr = "DELETE";
                    break;
                }
                default:
            }
        }
        log.info("(" + methodTypeStr + ") " + classRequestMappingStr + methodRequestMappingStr + " ("
                + joinPoint.getSignature().getName() + " -> " + joinPoint.getTarget().getClass() + ")");
    }
}
