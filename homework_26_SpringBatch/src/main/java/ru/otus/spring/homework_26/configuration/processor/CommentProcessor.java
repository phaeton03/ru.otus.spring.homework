package ru.otus.spring.homework_26.configuration.processor;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework_26.domain.h2.BookJPA;
import ru.otus.spring.homework_26.domain.h2.CommentJPA;
import ru.otus.spring.homework_26.domain.mongo.Book;
import ru.otus.spring.homework_26.domain.mongo.embedded.Comment;
import ru.otus.spring.homework_26.exception.BookNotFoundException;
import ru.otus.spring.homework_26.repository.jpa.BookJPARepository;
import ru.otus.spring.homework_26.repository.mongo.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CommentProcessor implements ItemProcessor<Comment, CommentJPA> {
    private final BookRepository bookRepository;
    private final BookJPARepository bookJPARepository;

    @Override
    public CommentJPA process(Comment comment) throws Exception {
        Book bookMongo = bookRepository.findByCommentsIsContaining(comment);
        BookJPA bookJPA = bookJPARepository.findByName(bookMongo.getName()).orElseThrow(BookNotFoundException::new);

        CommentJPA commentJPA = new CommentJPA();
        commentJPA.setComment(comment.getComment());
        commentJPA.setBook(bookJPA);

        return commentJPA;
    }
}