package ru.otus.spring.homework_9.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.homework_9.repository.BookDao;
import ru.otus.spring.homework_9.repository.CommentDao;
import ru.otus.spring.homework_9.domain.Book;
import ru.otus.spring.homework_9.domain.Comment;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
class CommentServiceTest {
    private final List<Comment> commentList = List.of(new Comment("comment1TEST"), new Comment("comment2TEST"));
    private final Long BOOK_ID = 10l;
    private final Book TEST_BOOK = new Book(BOOK_ID, "King Arthur", null, null, commentList);

    private CommentService commentService;

    @Mock
    BookDao bookDao;
    @Mock
    CommentDao commentDao;

    @BeforeEach
    void setUp() {
        commentService = new CommentService(bookDao, commentDao);
    }

    @Test
    void shouldNotBeEmptyGetAllCommentsByBook() {
        given(bookDao.getById(BOOK_ID)).willReturn(Optional.of(TEST_BOOK));
        assertThat(commentService.getAllCommentsByBook(BOOK_ID)).isNotEmpty().hasLineCount(6);
        verify(bookDao, times(1)).getById(BOOK_ID);
    }
}