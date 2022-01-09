package ru.otus.spring.homework_26.configuration.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework_26.domain.h2.AuthorJPA;
import ru.otus.spring.homework_26.domain.mongo.Author;

@Component
public class AuthorProcessor implements ItemProcessor<Author, AuthorJPA> {
    @Override
    public AuthorJPA process(Author author) throws Exception {
        AuthorJPA authorJPA = new AuthorJPA();
        authorJPA.setName(author.getName());
        return authorJPA;
    }
}