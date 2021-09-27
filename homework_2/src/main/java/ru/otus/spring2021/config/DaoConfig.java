package ru.otus.spring2021.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring2021.dao.AnswerDao;
import ru.otus.spring2021.dao.AnswerDaoImp;
import ru.otus.spring2021.dao.QuestionaryDao;
import ru.otus.spring2021.dao.QuestionaryDaoImp;

@PropertySource("classpath:application.properties")
@Configuration
public class DaoConfig {
    @Value("${questions.path}")
    private String questionsPath;
    @Value("${answer.path}")
    private String answerPath;

    @Bean
    public QuestionaryDao getQuestionaryDao() {
        return new QuestionaryDaoImp(questionsPath);
    }

    @Bean
    public AnswerDao getAnswerDao() {
        return new AnswerDaoImp(answerPath);
    }
}

