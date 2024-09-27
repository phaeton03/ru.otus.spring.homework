package ru.otus.spring.homework_11.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.otus.spring.homework_26.domain.mongo.Book;
import ru.otus.spring.homework_26.domain.mongo.embedded.Comment;
import ru.otus.spring.homework_26.repository.mongo.BookRepository;
import ru.otus.spring.homework_26.service.BookServiceImp;
import ru.otus.spring.homework_26.service.CommentServiceImp;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CommentJPAServiceImpTest {
    private final List<Comment> commentList = List.of(new Comment("comment1TEST"), new Comment("comment2TEST"));
    private final String BOOK_ID = "10l";
    private final Book TEST_BOOK = new Book(BOOK_ID, "King Arthur", null, null, commentList);

    private CommentServiceImp commentServiceImp;

    @Mock
    BookRepository bookRepository;

    @Mock
    BookServiceImp bookServiceImp;

    @BeforeEach
    void setUp() {
        commentServiceImp = new CommentServiceImp(bookServiceImp, bookRepository);
    }

    @Test
    void shouldNotBeEmptyGetAllCommentsByBook() {
        given(bookRepository.findById(BOOK_ID)).willReturn(Optional.of(TEST_BOOK));
        given(bookServiceImp.findBook(BOOK_ID)).willReturn(TEST_BOOK);
        assertThat(commentServiceImp.getAllCommentsByBook(BOOK_ID)).isNotEmpty().hasLineCount(6);
        verify(bookServiceImp, times(1)).findBook(BOOK_ID);
    }
}