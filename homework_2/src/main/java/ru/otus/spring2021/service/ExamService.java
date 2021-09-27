package ru.otus.spring2021.service;


import lombok.AllArgsConstructor;
import ru.otus.spring2021.dao.AnswerDao;
import ru.otus.spring2021.dao.QuestionaryDao;
import ru.otus.spring2021.model.Quiz;

import java.util.Scanner;

@AllArgsConstructor
public class ExamService {
    private final QuestionaryDao questionaryDAO;
    private final UserService userService;
    private final AnswerDao answerDao;
    private final int passTest;

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
        if (checkTest()) {
            System.out.println("Congratulations! You pass the exam!");
        } else {
            System.out.println("Sorry, you dont pass the exam. Try another time!");
        }
    }

    private boolean checkTest() {
        int failCount = 0;
        Scanner scanner = new Scanner(System.in);
        try {
            for (String answer : answerDao.getAnswers().getDescription()) {
                String yourAnswer = scanner.nextLine();
                System.out.printf("Correct answer: %s Your answer: %s %n", answer, yourAnswer);
                if (!answer.equalsIgnoreCase(yourAnswer)) {
                    failCount++;
                }
            }
        } catch (Exception e) {

        }
        return failCount <= passTest;
    }
}


