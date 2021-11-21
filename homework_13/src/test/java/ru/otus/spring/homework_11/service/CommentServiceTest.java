package ru.otus.spring.homework_11.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.otus.spring.homework_13.repository.*;
import ru.otus.spring.homework_13.domain.Book;
import ru.otus.spring.homework_13.domain.Comment;
import ru.otus.spring.homework_13.service.BookService;
import ru.otus.spring.homework_13.service.CommentService;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CommentServiceTest {
    private final List<Comment> commentList = List.of(new Comment("comment1TEST"), new Comment("comment2TEST"));
    private final String BOOK_ID = "10l";
    private final Book TEST_BOOK = new Book(BOOK_ID, "King Arthur", null, null, commentList);

    private CommentService commentService;

    @Mock
    BookRepository bookDao;

    @Mock
    CommentRepository commentDao;

    @Mock
    BookService bookService;

    @BeforeEach
    void setUp() {
        commentService = new CommentService(bookService, bookDao, commentDao);
    }

    @Test
    void shouldNotBeEmptyGetAllCommentsByBook() {
        given(bookDao.findById(BOOK_ID)).willReturn(Optional.of(TEST_BOOK));
        given(bookService.findBook(BOOK_ID)).willReturn(TEST_BOOK);
        assertThat(commentService.getAllCommentsByBook(BOOK_ID)).isNotEmpty().hasLineCount(6);
        verify(bookService, times(1)).findBook(BOOK_ID);
    }
}