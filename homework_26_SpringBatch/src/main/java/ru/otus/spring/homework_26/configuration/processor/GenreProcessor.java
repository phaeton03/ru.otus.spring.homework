package ru.otus.spring.homework_26.configuration.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework_26.domain.h2.GenreJPA;
import ru.otus.spring.homework_26.domain.mongo.Book;
import ru.otus.spring.homework_26.domain.mongo.embedded.Genre;

import java.util.HashSet;
import java.util.Set;

@Component
public class GenreProcessor implements ItemProcessor<Genre, GenreJPA> {

    @Override
    public GenreJPA process(Genre genreMongo) throws Exception {
        GenreJPA genreJPA = new GenreJPA();
        genreJPA.setName(genreMongo.getName());

        return genreJPA;
    }
}