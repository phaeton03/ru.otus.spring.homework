package ru.otus.spring.homework_29.service;

import ru.otus.spring.homework_29.domain.adult.AdultAnimal;
import ru.otus.spring.homework_29.domain.baby.BabyAnimal;

public interface Transformable {
    AdultAnimal makeOrdinaryTransformation(BabyAnimal babyAnimal);
    AdultAnimal makeMetamorphosisTransformation(BabyAnimal babyAnimal);
}