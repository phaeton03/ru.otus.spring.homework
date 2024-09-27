package ru.otus.spring.homework_29.configuration.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.spring.homework_29.domain.adult.AdultAnimal;
import ru.otus.spring.homework_29.domain.baby.BabyAnimal;

import java.util.List;

@MessagingGateway
public interface LifeCycle {

    @Gateway(requestChannel = "babyChannel", replyChannel = "adultChannel")
    List<AdultAnimal> process(List<BabyAnimal> babyAnimalList);
}