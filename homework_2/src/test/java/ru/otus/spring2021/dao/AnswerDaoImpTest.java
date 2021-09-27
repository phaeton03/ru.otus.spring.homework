package ru.otus.spring2021.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring2021.model.Answer;

import java.util.List;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class AnswerDaoImpTest {
    private final Answer answer = new Answer(List.of("1","2","3"));
 
    @Mock
    AnswerDao answerDao;

    @Test
    void checkAnswers() throws Exception {
        given(answerDao.getAnswers()).willReturn(answer);
        assertThat(answerDao.getAnswers().getDescription()).hasSize(3);
    }
}