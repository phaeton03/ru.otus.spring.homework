package ru.otus.spring2021.service;

import ru.otus.spring2021.dao.QuestionaryDAOImp;
import ru.otus.spring2021.model.Quiz;

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
