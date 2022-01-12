package ru.otus.spring.homework_29.helper;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnimalUtil<T> {
    public T getAnimal(List<Class<? extends T>> animalClasses, int pointer, int counter) {
        T animal = null;

        try {
            animal =
                    animalClasses.get(pointer)
                            .getConstructor(String.class)
                            .newInstance("pet #" + counter);
        } catch (Exception e) {
            System.err.println(e);
        }

        return animal;
    }

    public T getAnimal(Class<? extends T> animalClass, int counter) {
        return getAnimal(List.of(animalClass), 0, counter);
    }
}