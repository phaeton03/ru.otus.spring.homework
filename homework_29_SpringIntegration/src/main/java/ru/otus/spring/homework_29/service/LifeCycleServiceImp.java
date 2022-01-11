package ru.otus.spring.homework_29.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework_29.configuration.gateway.LifeCycle;
import ru.otus.spring.homework_29.domain.adult.AdultAnimal;
import ru.otus.spring.homework_29.domain.baby.BabyAnimal;
import ru.otus.spring.homework_29.factory.AnimalCreatable;
import ru.otus.spring.homework_29.helper.Counter;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LifeCycleServiceImp implements LifeCycleService {
    private final ApplicationContext appContext;
    private final AnimalCreatable babyAnimalFactory;
    private final Counter babyCounter;
    private final Counter adultCounter;

    @Autowired
    public LifeCycleServiceImp(ApplicationContext appContext,
                               AnimalCreatable babyAnimalFactory,
                               @Qualifier("babyAnimal") Counter babyCounter,
                               @Qualifier("adultAnimal") Counter adultCounter) {

        this.appContext = appContext;
        this.babyAnimalFactory = babyAnimalFactory;
        this.babyCounter = babyCounter;
        this.adultCounter = adultCounter;
    }


    public void launchCycleOfLife(Long size) {
        LifeCycle lifeCycle = appContext.getBean(LifeCycle.class);
        List<BabyAnimal> babyAnimals = babyAnimalFactory.babyListCreate(size);

        System.out.println("New babyAnimals: " +
                babyAnimals.stream().map(BabyAnimal::toString)
                        .collect(Collectors.joining(",")));

        final List<AdultAnimal> adultAnimals = lifeCycle.process(babyAnimals);

        System.out.println("New adultAnimals: " +
                adultAnimals.stream().map(AdultAnimal::toString)
                        .collect(Collectors.joining(",")));

        resetCounters();
    }

    private void resetCounters() {
        adultCounter.resetCounter();
        babyCounter.resetCounter();
    }
}