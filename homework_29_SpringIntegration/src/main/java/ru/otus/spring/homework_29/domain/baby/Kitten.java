package ru.otus.spring.homework_29.domain.baby;

public class Kitten extends BabyAnimal {
    public Kitten(String name) {
        super(name);
        isMetamorphosis = false;
    }
}