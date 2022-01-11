package ru.otus.spring.homework_29.dao;

import ru.otus.spring.homework_29.domain.adult.AdultAnimal;
import ru.otus.spring.homework_29.domain.baby.BabyAnimal;

import java.util.List;

public interface AnimalDao {
    List<Class<? extends BabyAnimal>> getBabyList();

    Class<? extends AdultAnimal> getAdultAnimal(Class<? extends BabyAnimal> babyAnimalClass);
}