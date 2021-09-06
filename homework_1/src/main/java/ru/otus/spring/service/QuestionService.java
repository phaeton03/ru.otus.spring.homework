package ru.otus.spring.service;

import ru.otus.spring.dao.QuestionaryDAOImp;
import ru.otus.spring.model.Quiz;

import java.io.IOException;

public class QuestionService {
    private final QuestionaryDAOImp questionaryDAO;

    public QuestionService(QuestionaryDAOImp questionaryDAO) {
        this.questionaryDAO = questionaryDAO;
    }

    public void showQuestions() {
        try {
            Quiz quiz = questionaryDAO.getQuestions();
            quiz.getDescription().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
