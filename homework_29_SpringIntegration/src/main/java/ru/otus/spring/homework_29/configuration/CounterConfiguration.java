package ru.otus.spring.homework_29.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.homework_29.helper.Counter;

@Configuration
public class CounterConfiguration {

    @Bean("babyAnimal")
    public Counter getBabyCounter() {
        return new Counter();
    }

    @Bean("adultAnimal")
    public Counter getAdultCounter() {
        return new Counter();
    }
}