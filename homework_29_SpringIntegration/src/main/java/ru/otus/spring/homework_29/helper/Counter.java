package ru.otus.spring.homework_29.helper;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private final Integer START_VALUE = 1;
    private final AtomicInteger COUNTER = new AtomicInteger(START_VALUE);

    public Integer atomicIncrement() {
        return COUNTER.getAndIncrement();
    }

    public void resetCounter() {
        COUNTER.set(START_VALUE);
    }
}