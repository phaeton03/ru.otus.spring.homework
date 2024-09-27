package ru.otus.spring.homework_32.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.homework_32.domain.Author;
import ru.otus.spring.homework_32.domain.Book;
import ru.otus.spring.homework_32.domain.Comment;
import ru.otus.spring.homework_32.domain.Genre;
import ru.otus.spring.homework_32.service.BookService;
import ru.otus.spring.homework_32.service.CommentService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.security.test.context.support.WithMockUser;
import ru.otus.spring.homework_32.service.UserService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest()
@AutoConfigureMockMvc
class BookControllerTest {
    private final String COMMENT_MSG_1 = "comment1TEST";
    private final String COMMENT_MSG_2 = "comment2TEST";
    private final Comment COMMENT_1 = new Comment(COMMENT_MSG_1);
    private final Comment COMMENT_2 = new Comment(COMMENT_MSG_2);
    private final List<Comment> commentList = List.of(COMMENT_1, COMMENT_2);
    private final Genre GENRE = new Genre(1L, "TEST_GENRE", new ArrayList<Book>());
    private final Author AUTHOR = new Author(1L, "TEST_AUTHOR", new ArrayList<Book>());
    private final Long BOOK_ID_1 = 10l;
    private final Long BOOK_ID_2 = 20l;
    private final Book TEST_BOOK_1 = new Book(BOOK_ID_1, "King Arthur", AUTHOR, GENRE, commentList);
    private final Book TEST_BOOK_2 = new Book(BOOK_ID_2, "King Lir", AUTHOR, GENRE, commentList);

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private UserService userService;

    @MockBean
    private CommentService commentService;

    @WithMockUser(username = "user", authorities = {"ROLE_USER"})
    @Test
    @SneakyThrows
    void shouldFindAllBooks() {
        when(bookService.findAllBooks()).thenReturn(List.of(TEST_BOOK_1, TEST_BOOK_2));

        mvc.perform(get("/")).andDo(print()).andExpect(status().isOk()).andExpect(view().name("books"));
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "user", authorities = {"ROLE_USER"})
    void shouldFindBooksComments() {
        when(commentService.getAllCommentsByBook(BOOK_ID_1)).thenReturn(commentList);

        mvc.perform(get("/comments").param("bookId", String.valueOf(BOOK_ID_1)))
                .andExpect(status().isOk())
                .andExpect(view().name("comments"));
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "user", authorities = {"ROLE_USER"})
    void shouldFindBooksByGenre() {
        when(bookService.findBooksByGenre(GENRE.getName())).thenReturn(List.of(TEST_BOOK_1, TEST_BOOK_2));

        mvc.perform(get("/").param("genre", GENRE.getName()))
                .andExpect(status().isOk())
                .andExpect(view().name("books"));
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "user", authorities = {"ROLE_USER"})
    void shouldFindBooksByAuthor() {
        when(bookService.findBooksByAuthor(AUTHOR.getName())).thenReturn(List.of(TEST_BOOK_1, TEST_BOOK_2));

        mvc.perform(get("/").param("author", AUTHOR.getName()))
                .andExpect(status().isOk())
                .andExpect(view().name("books"));
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "user", authorities = {"ROLE_USER"})
    void shouldFindBooksById() {
        when(bookService.findBook(BOOK_ID_1)).thenReturn(TEST_BOOK_1);

        mvc.perform(get("/").param("bookId", String.valueOf(BOOK_ID_1)))
                .andExpect(status().isOk())
                .andExpect(view().name("books"));
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "user", authorities = {"ROLE_USER"})
    void publishComment() {
        doNothing().when(commentService).editComment(1L, COMMENT_MSG_1);

        mvc.perform(get("/comments")
                        .param("bookId", String.valueOf(BOOK_ID_1))
                        .param("msg", COMMENT_MSG_1))
                .andExpect(status().isOk())
                .andExpect(view().name("comments"));
    }
}