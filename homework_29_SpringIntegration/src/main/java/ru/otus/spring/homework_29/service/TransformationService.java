package ru.otus.spring.homework_29.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework_29.dao.AnimalDao;
import ru.otus.spring.homework_29.domain.adult.AdultAnimal;
import ru.otus.spring.homework_29.domain.baby.BabyAnimal;
import ru.otus.spring.homework_29.helper.AnimalUtil;
import ru.otus.spring.homework_29.helper.Counter;
import ru.otus.spring.homework_29.type.TransformationType;

@Service
public class TransformationService implements Transformable {

    private final Counter counter;
    private final AnimalDao animalDao;
    private final AnimalUtil<AdultAnimal> animalUtil;

    @Value("${lifecycle.transformation.ordinary.duration}")
    private Long ordinaryTransformationTime;

    @Value("${lifecycle.transformation.metamorphosis.duration}")
    private Long metamorphosisTransformationTime;

    @Autowired
    public TransformationService(AnimalDao animalDao, AnimalUtil<AdultAnimal> animalUtil, @Qualifier("adultAnimal") Counter counter) {
        this.animalDao = animalDao;
        this.animalUtil = animalUtil;
        this.counter = counter;
    }


    public AdultAnimal makeOrdinaryTransformation(BabyAnimal babyAnimal) {
        return traceTransformation(babyAnimal, ordinaryTransformationTime, TransformationType.ORDINARY);
    }

    public AdultAnimal makeMetamorphosisTransformation(BabyAnimal babyAnimal) {
        return traceTransformation(babyAnimal, metamorphosisTransformationTime, TransformationType.METAMORPHOSIS);
    }

    private AdultAnimal traceTransformation(BabyAnimal babyAnimal, Long duration, TransformationType type) {
        final Class<? extends AdultAnimal> adultAnimalClass = animalDao.getAdultAnimal(babyAnimal.getClass());
        final AdultAnimal adultAnimal = animalUtil.getAnimal(adultAnimalClass, counter.atomicIncrement());

        try {
            System.out.println(type.name() + " transformation is started from " + babyAnimal);
            Thread.sleep(duration);
            System.out.println(type.name() + " transformation is over. Here is " + adultAnimal);
        } catch (Exception e) {
            System.err.println(e);
        }

        return adultAnimal;
    }
}