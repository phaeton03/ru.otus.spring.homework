package ru.otus.spring.homework_7.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.spring.homework_7.dao.BookDao;
import ru.otus.spring.homework_7.dao.CommentDao;
import ru.otus.spring.homework_7.domain.Book;
import ru.otus.spring.homework_7.domain.Comment;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

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