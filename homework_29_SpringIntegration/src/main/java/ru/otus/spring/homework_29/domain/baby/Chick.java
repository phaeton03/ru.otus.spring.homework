package ru.otus.spring.homework_29.domain.baby;

public class Chick extends BabyAnimal {

    public Chick(String name) {
        super(name);
        isMetamorphosis = false;
    }
}