package ru.otus.spring.homework_32.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.homework_32.service.AdminService;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerTest {
    private final Long BOOK_ID = 1L;
    private final Long COMMENT_ID = 1L;
    private final String EDIT_MESSAGE = "EDIT_MESSAGE";

    @Autowired
    private MockMvc mvc;

    @MockBean
    AdminService adminService;

    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    @Test
    @SneakyThrows
    void shouldGetMenu() {
        mvc.perform(get("/admin/")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    @SneakyThrows
    void deleteBook() {
        doNothing().when(adminService).deleteComment(COMMENT_ID);

        mvc.perform(post("/admin/delete-comment/{commentId}", COMMENT_ID)
                .param("commentId", String.valueOf(COMMENT_ID)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    @SneakyThrows
    void deleteComment() {
        doNothing().when(adminService).deleteBook(BOOK_ID);

        mvc.perform(post("/admin/delete-book/{bookId}", BOOK_ID).param("bookId", String.valueOf(BOOK_ID))).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    @SneakyThrows
    void editComment() {
        doNothing().when(adminService).editComment(COMMENT_ID, EDIT_MESSAGE);

        mvc.perform(post("/admin/edit-comment")
                .param("commentId",String.valueOf(COMMENT_ID))
                .param("message",EDIT_MESSAGE))
                .andExpect(status().isOk());
    }
}