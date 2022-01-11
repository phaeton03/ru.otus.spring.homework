package ru.otus.spring.homework_29.domain.baby;

public abstract class BabyAnimal {
    private String name;
    protected Boolean isMetamorphosis;

    public BabyAnimal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Boolean isMetamorphosis() {
        return isMetamorphosis;
    }

    @Override
    public String toString() {
        return "BabyAnimal: " + this.getClass().getSimpleName() + " his name is: " + name;
    }
}