package ru.otus.spring.homework_26.configuration.reader;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework_26.configuration.helper.ReaderHelper;
import ru.otus.spring.homework_26.domain.h2.GenreJPA;
import ru.otus.spring.homework_26.domain.mongo.embedded.Genre;
import ru.otus.spring.homework_26.repository.jpa.GenreJPARepository;
import ru.otus.spring.homework_26.repository.mongo.GenreCustomRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GenreReader implements ItemReader<Genre> {
    private final GenreCustomRepository genreCustomRepository;
    private final GenreJPARepository genreJPARepository;
    private final ReaderHelper<Genre> readerHelper;

    private ItemReader<Genre> reader;


    @Override
    public Genre read() throws Exception {
        if (reader == null) {
            reader = new IteratorItemReader<>(genres());
        }
        return reader.read();
    }

    private List<Genre> genres() {
        List<Genre> result = genreCustomRepository.findAllGenres();
        Integer lastRead = genreJPARepository.findAll().size();

        readerHelper.clearList(result, 0, lastRead);

        return result;
    }
}