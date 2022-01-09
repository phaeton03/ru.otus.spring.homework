package ru.otus.spring.homework_26.configuration.reader;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework_26.configuration.helper.ReaderHelper;
import ru.otus.spring.homework_26.domain.h2.BookJPA;
import ru.otus.spring.homework_26.domain.mongo.Book;
import ru.otus.spring.homework_26.repository.jpa.BookJPARepository;
import ru.otus.spring.homework_26.repository.mongo.BookRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookReader implements ItemReader<Book> {
    private final BookJPARepository bookJPARepository;
    private final BookRepository bookRepository;
    private final ReaderHelper<Book> readerHelper;
    private ItemReader<Book> reader;

    @Override
    public Book read() throws Exception {
        if (reader == null) {
            reader = new IteratorItemReader<>(books());
        }
        return reader.read();
    }

    private List<Book> books() {
        List<Book> result = bookRepository.findAll();
        Integer lastRead = bookJPARepository.findAll().size();

        readerHelper.clearList(result, 0, lastRead);

        return result;
    }
}