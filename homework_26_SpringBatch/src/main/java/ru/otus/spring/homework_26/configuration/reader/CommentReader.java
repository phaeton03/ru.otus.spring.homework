package ru.otus.spring.homework_26.configuration.reader;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework_26.configuration.helper.ReaderHelper;
import ru.otus.spring.homework_26.domain.h2.CommentJPA;
import ru.otus.spring.homework_26.domain.mongo.embedded.Comment;
import ru.otus.spring.homework_26.repository.jpa.CommentJPARepository;
import ru.otus.spring.homework_26.repository.mongo.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CommentReader implements ItemReader<Comment> {
    private final BookRepository bookRepository;
    private final CommentJPARepository commentJPARepository;
    private final ReaderHelper<Comment> readerHelper;
    private ItemReader<Comment> reader;

    @Override
    public Comment read() throws Exception {
        if (reader == null) {
            reader = new IteratorItemReader<>((comments()));
        }
        return reader.read();
    }

    private List<Comment> comments() {
        List<Comment> result = bookRepository.findAll().stream().flatMap(book -> book.getComments().stream()).collect(Collectors.toList());
        Integer lastRead = commentJPARepository.findAll().size();

        readerHelper.clearList(result, 0, lastRead);

        return result;
    }
}