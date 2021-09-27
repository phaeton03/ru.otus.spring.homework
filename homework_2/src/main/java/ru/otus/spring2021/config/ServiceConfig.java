package ru.otus.spring2021.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring2021.dao.AnswerDao;
import ru.otus.spring2021.dao.QuestionaryDao;
import ru.otus.spring2021.service.ExamService;
import ru.otus.spring2021.service.UserService;

@PropertySource("classpath:application.properties")
@Configuration
public class ServiceConfig {
    @Value("${pass.test}")
    private int passTest;

    @Bean
    public ExamService getQuestionService(QuestionaryDao questionaryDAO, UserService userService, AnswerDao answerDao) {
        return new ExamService(questionaryDAO, userService, answerDao, passTest);
    }

    @Bean
    public UserService getUserService() {
        return new UserService();
    }
}
