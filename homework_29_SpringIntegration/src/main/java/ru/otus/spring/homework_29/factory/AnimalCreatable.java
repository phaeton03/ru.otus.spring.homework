package ru.otus.spring.homework_29.factory;

import ru.otus.spring.homework_29.domain.baby.BabyAnimal;

import java.util.List;

public interface AnimalCreatable {
    BabyAnimal babyCreate();

    List<BabyAnimal> babyListCreate(Long size);
}