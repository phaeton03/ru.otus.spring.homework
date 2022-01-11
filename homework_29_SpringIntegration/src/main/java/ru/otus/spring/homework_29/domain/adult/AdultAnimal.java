package ru.otus.spring.homework_29.domain.adult;

public abstract class AdultAnimal {
    private String name;

    public AdultAnimal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "AdultAnimal: " + this.getClass().getSimpleName() + " his name is: " + name;
    }
}