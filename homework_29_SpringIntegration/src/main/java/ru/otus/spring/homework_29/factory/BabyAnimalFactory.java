package ru.otus.spring.homework_29.factory;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework_29.dao.AnimalDao;
import ru.otus.spring.homework_29.domain.baby.BabyAnimal;
import ru.otus.spring.homework_29.helper.AnimalUtil;
import ru.otus.spring.homework_29.helper.Counter;

import java.util.ArrayList;
import java.util.List;

@Component
public class BabyAnimalFactory implements AnimalCreatable {
    private final AnimalDao animalDao;
    private final AnimalUtil<BabyAnimal> babyUtil;
    private final Counter counter;

    @Autowired
    public BabyAnimalFactory(AnimalDao animalDao, AnimalUtil<BabyAnimal> babyUtil, @Qualifier("babyAnimal") Counter counter) {
        this.animalDao = animalDao;
        this.babyUtil = babyUtil;
        this.counter = counter;
    }

    @Override
    public BabyAnimal babyCreate() {
        final List<Class<? extends BabyAnimal>> babyClasses = animalDao.getBabyList();

        return babyUtil.getAnimal(babyClasses, RandomUtils.nextInt(0, babyClasses.size()), counter.atomicIncrement());
    }

    @Override
    public List<BabyAnimal> babyListCreate(Long size) {
        List<BabyAnimal> result = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            result.add(babyCreate());
        }

        return result;
    }
}