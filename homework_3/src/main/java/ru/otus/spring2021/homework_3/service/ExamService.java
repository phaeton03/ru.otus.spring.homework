package ru.otus.spring2021.homework_3.service;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring2021.homework_3.config.ServiceConfig;
import ru.otus.spring2021.homework_3.dao.AnswerDao;
import ru.otus.spring2021.homework_3.dao.QuestionaryDao;
import ru.otus.spring2021.homework_3.model.Quiz;
import ru.otus.spring2021.homework_3.wrapper.MessageSourceWrapper;

import java.util.Scanner;

@Service
@AllArgsConstructor
public class ExamService {
    private final QuestionaryDao questionaryDAO;
    private final UserService userService;
    private final TestingService testingService;
    private final MessageSourceWrapper msg;

    public void showQuestions() {
        userService.signIn();
        try {
            Quiz quiz = questionaryDAO.getQuestions();
            quiz.getDescription().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void passExam() {
        showQuestions();
        if (testingService.checkTest()) {
            System.out.println(msg.getMessage("string.congratulations"));
        } else {
            System.out.println(msg.getMessage("string.sorry"));
        }
    }
}


