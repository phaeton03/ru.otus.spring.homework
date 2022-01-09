package ru.otus.spring.homework_26.configuration.reader;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework_26.configuration.helper.ReaderHelper;
import ru.otus.spring.homework_26.domain.h2.AuthorJPA;
import ru.otus.spring.homework_26.domain.h2.BookJPA;
import ru.otus.spring.homework_26.domain.h2.CommentJPA;
import ru.otus.spring.homework_26.domain.mongo.Author;
import ru.otus.spring.homework_26.domain.mongo.Book;
import ru.otus.spring.homework_26.repository.jpa.AuthorJPARepository;
import ru.otus.spring.homework_26.repository.mongo.AuthorRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthorReader implements ItemReader<Author> {
    private final AuthorRepository authorRepository;
    private final AuthorJPARepository authorJPARepository;
    private final ReaderHelper<Author> readerHelper;
    private ItemReader<Author> reader;

    @Override
    public Author read() throws Exception {
        if (reader == null) {
            reader = new IteratorItemReader<>(authors());
        }
        return reader.read();
    }

    private List<Author> authors() {
        List<Author> result = authorRepository.findAll();
        Integer lastRead = authorJPARepository.findAll().size();

        readerHelper.clearList(result, 0, lastRead);

        return result;
    }
}