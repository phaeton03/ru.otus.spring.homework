package ru.otus.spring2021.homework_3.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class ExceptionHandler {

    @AfterThrowing(value = "within(ru.otus.spring2021.homework_3..*)", throwing = "e")
    public void log(JoinPoint joinPoint, Throwable e) {
        System.out.println(joinPoint + " -> " + e);
    }
}
