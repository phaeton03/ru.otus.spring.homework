package ru.otus.spring.homework_13.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.spring.homework_13.domain.embedded.Author;
import ru.otus.spring.homework_13.domain.Book;
import ru.otus.spring.homework_13.domain.Comment;
import ru.otus.spring.homework_13.domain.embedded.Genre;
import ru.otus.spring.homework_13.repository.BookRepository;
import ru.otus.spring.homework_13.repository.CommentRepository;

import java.util.List;

@ChangeLog(order = "001")
public class InitMongoDB {
    private Comment comment1 = new Comment("comment1");
    private Comment comment2 = new Comment("comment2");
    private Comment comment3 = new Comment("comment3");
    private Comment comment4 = new Comment("comment4");
    private Comment comment5 = new Comment("comment5");
    private Comment comment6 = new Comment("comment6");
    private Comment comment7 = new Comment("comment7");
    private Comment comment8 = new Comment("comment8");
    private Comment comment9 = new Comment("comment9");

    private Author pushkin = new Author("Pushin");
    private Author dostoevskiy = new Author("Dostoevskiy");
    private Author lermontov = new Author("Lermontov");
    private Author esenin = new Author("Esenin");

    private Genre comedy = new Genre("comedy");
    private Genre tradegy = new Genre("tradegy");
    private Genre drama = new Genre("drama");
    private Genre action = new Genre("action");
    private Genre novel = new Genre("novel");
    private Genre horror = new Genre("horror");
    private Genre satira = new Genre("satira");
    private Genre poetry = new Genre("poetry");
    private Genre detective = new Genre("detective");
    private Genre fairyTale = new Genre("fairyTale");

    private Book saltanTheTsar = new Book("Сказ о царе Салтане", pushkin, fairyTale, List.of(comment1, comment2));
    private Book konekGorbunok = new Book("Конек Горбунок", pushkin, fairyTale, List.of(comment3));
    private Book goldenFish = new Book("Золотая рыбка", pushkin, fairyTale);
    private Book ruslanAndLudmila = new Book("Руслан и Людмила", pushkin, fairyTale);
    private Book heroyNashegoVremeni = new Book("Герой нашего времени", lermontov, poetry, List.of(comment4, comment5));
    private Book poetryLermontov = new Book("Лермонтов,стихи", lermontov, poetry);
    private Book prestuplenieAndNakazanie = new Book("Преступление и наказание", dostoevskiy, drama, List.of(comment6));
    private Book besi = new Book("Бесы", dostoevskiy, tradegy, List.of(comment7));
    private Book cherniiChelovek = new Book("Черный человек", esenin, poetry);
    private Book poetryEsenin = new Book("Есенин,стихи", esenin, poetry);


    @ChangeSet(order = "001", id = "dropDB", author = "NikolskiyNS", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "002", id = "initComment", author = "NikolskiyNS", runAlways = true)
    public void initComment(CommentRepository commentRepository) {
        commentRepository.saveAll(
                List.of(
                        comment1,
                        comment2,
                        comment3,
                        comment4,
                        comment5,
                        comment6,
                        comment7,
                        comment8,
                        comment9
                ));
    }

    @ChangeSet(order = "003", id = "initBook", author = "NikolskiyNS", runAlways = true)
    public void initBook(BookRepository bookRepository) {

        bookRepository.saveAll(
                List.of(
                        saltanTheTsar,
                        konekGorbunok,
                        goldenFish,
                        ruslanAndLudmila,
                        heroyNashegoVremeni,
                        poetryLermontov,
                        prestuplenieAndNakazanie,
                        besi,
                        cherniiChelovek,
                        poetryEsenin
                ));
    }
}