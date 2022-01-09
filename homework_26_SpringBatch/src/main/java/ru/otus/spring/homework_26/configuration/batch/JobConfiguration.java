package ru.otus.spring.homework_26.configuration.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.homework_26.configuration.processor.AuthorProcessor;
import ru.otus.spring.homework_26.configuration.processor.BookProcessor;
import ru.otus.spring.homework_26.configuration.processor.CommentProcessor;
import ru.otus.spring.homework_26.configuration.processor.GenreProcessor;
import ru.otus.spring.homework_26.configuration.reader.AuthorReader;
import ru.otus.spring.homework_26.configuration.reader.BookReader;
import ru.otus.spring.homework_26.configuration.reader.CommentReader;
import ru.otus.spring.homework_26.configuration.reader.GenreReader;
import ru.otus.spring.homework_26.domain.h2.AuthorJPA;
import ru.otus.spring.homework_26.domain.h2.BookJPA;
import ru.otus.spring.homework_26.domain.h2.CommentJPA;
import ru.otus.spring.homework_26.domain.h2.GenreJPA;
import ru.otus.spring.homework_26.domain.mongo.Author;
import ru.otus.spring.homework_26.domain.mongo.Book;
import ru.otus.spring.homework_26.domain.mongo.embedded.Comment;
import ru.otus.spring.homework_26.domain.mongo.embedded.Genre;
import ru.otus.spring.homework_26.repository.jpa.AuthorJPARepository;
import ru.otus.spring.homework_26.repository.jpa.BookJPARepository;
import ru.otus.spring.homework_26.repository.jpa.CommentJPARepository;
import ru.otus.spring.homework_26.repository.jpa.GenreJPARepository;


@Configuration
public class JobConfiguration {
    private static final int CHUNK_SIZE = 5;
    public static final String MIGRATION_USER_JOB_NAME = "migrationUserJob";

    @Autowired
    private AuthorJPARepository authorJPARepository;

    @Autowired
    private GenreJPARepository genreJPARepository;

    @Autowired
    private CommentJPARepository commentJPARepository;

    @Autowired
    private BookJPARepository bookJPARepository;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private AuthorProcessor authorProcessor;

    @Autowired
    private CommentProcessor commentProcessor;

    @Autowired
    private GenreProcessor genreProcessor;

    @Autowired
    private BookProcessor bookProcessor;

    @Autowired
    private GenreReader genreReader;

    @Autowired
    private CommentReader commentReader;

    @Autowired
    private AuthorReader authorReader;

    @Autowired
    private BookReader bookReader;

    @Bean
    public RepositoryItemWriter<AuthorJPA> writerAuthor() {

        return new RepositoryItemWriterBuilder<AuthorJPA>()
                .repository(authorJPARepository)
                .build();
    }

    @Bean
    public RepositoryItemWriter<GenreJPA> writerGenre() {

        return new RepositoryItemWriterBuilder<GenreJPA>()
                .repository(genreJPARepository)
                .build();
    }

    @Bean
    public RepositoryItemWriter<CommentJPA> writerComment() {

        return new RepositoryItemWriterBuilder<CommentJPA>()
                .repository(commentJPARepository)
                .build();
    }

    @Bean
    public RepositoryItemWriter<BookJPA> writerBook() {

        return new RepositoryItemWriterBuilder<BookJPA>()
                .repository(bookJPARepository)
                .build();
    }

    @Bean
    public Step createStepAuthor(RepositoryItemWriter<AuthorJPA> writer) {
        return stepBuilderFactory
                .get("authorStep")
                .<Author, AuthorJPA>chunk(CHUNK_SIZE)
                .reader(authorReader)
                .processor(authorProcessor)
                .writer(writer)
                .build();
    }

    @Bean
    public Step createStepGenre(RepositoryItemWriter<GenreJPA> writer) {
        return stepBuilderFactory
                .get("genreStep")
                .<Genre, GenreJPA>chunk(CHUNK_SIZE)
                .reader(genreReader)
                .processor(genreProcessor)
                .writer(writer)
                .build();
    }

    @Bean
    public Step createStepBook(RepositoryItemWriter<BookJPA> writer) {
        return stepBuilderFactory
                .get("bookStep")
                .<Book, BookJPA>chunk(CHUNK_SIZE)
                .reader(bookReader)
                .processor(bookProcessor)
                .writer(writer)
                .build();
    }

    @Bean
    public Step createStepComments(RepositoryItemWriter<CommentJPA> writer) {
        return stepBuilderFactory
                .get("commentStep")
                .<Comment, CommentJPA>chunk(CHUNK_SIZE)
                .reader(commentReader)
                .processor(commentProcessor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job migrationUserJob(Step createStepAuthor, Step createStepGenre, Step createStepBook, Step createStepComments) {
        return jobBuilderFactory.get(MIGRATION_USER_JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .flow(createStepAuthor)
                .next(createStepGenre)
                .next(createStepBook)
                .next(createStepComments)
                .end()
                .build();
    }
}