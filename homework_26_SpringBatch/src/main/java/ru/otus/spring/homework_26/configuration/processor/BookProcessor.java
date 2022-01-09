package ru.otus.spring.homework_26.configuration.processor;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework_26.domain.h2.BookJPA;
import ru.otus.spring.homework_26.domain.mongo.Book;
import ru.otus.spring.homework_26.exception.AuthorNotFoundException;
import ru.otus.spring.homework_26.exception.GenreNotFoundException;
import ru.otus.spring.homework_26.repository.jpa.AuthorJPARepository;
import ru.otus.spring.homework_26.repository.jpa.GenreJPARepository;

@RequiredArgsConstructor
@Component
public class BookProcessor implements ItemProcessor<Book, BookJPA> {
    private final AuthorJPARepository authorJPARepository;
    private final GenreJPARepository genreJPARepository;

    @Override
    public BookJPA process(Book book) throws Exception {
        BookJPA bookJPA = new BookJPA();
        bookJPA.setAuthorJPA(authorJPARepository.findAuthorByName(book.getAuthor().getName()).orElseThrow(AuthorNotFoundException::new));
        bookJPA.setGenreJPA(genreJPARepository.findGenreByName(book.getGenre().getName()).orElseThrow(GenreNotFoundException::new));
        bookJPA.setName(book.getName());

        return bookJPA;
    }
}