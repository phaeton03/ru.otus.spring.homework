package ru.otus.spring.homework_29.dao;

import ru.otus.spring.homework_29.domain.adult.*;
import ru.otus.spring.homework_29.domain.baby.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AnimalDaoImp implements AnimalDao {
    private static Map<Class<? extends BabyAnimal>, Class<? extends AdultAnimal>> mapAnimal =
            Map.of(Caterpillar.class, Butterfly.class,
                    Chick.class, Chicken.class,
                    Kitten.class, Cat.class,
                    Puppy.class, Dog.class,
                    Toddler.class, Human.class);

    @Override
    public List<Class<? extends BabyAnimal>> getBabyList() {
        return new ArrayList<>(mapAnimal.keySet());
    }

    @Override
    public Class<? extends AdultAnimal> getAdultAnimal(Class<? extends BabyAnimal> babyAnimalClass) {
        return mapAnimal.get(babyAnimalClass);
    }
}