package ru.otus.spring2021.homework_3.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring2021.homework_3.dao.QuestionaryDao;
import ru.otus.spring2021.homework_3.model.Quiz;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ExamServiceTest {

        private final Quiz quiz = new Quiz(List.of("1", "2", "3"));

        @Mock
        private QuestionaryDao questionaryDao;

        @Test
        void howMuchQuestionsTest() throws Exception {
            given(questionaryDao.getQuestions()).willReturn(quiz);
            assertThat(questionaryDao.getQuestions().getDescription()).hasSize(3);
        }
    }
