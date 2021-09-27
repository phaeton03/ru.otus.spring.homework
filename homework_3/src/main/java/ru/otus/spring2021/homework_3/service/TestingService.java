package ru.otus.spring2021.homework_3.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring2021.homework_3.config.ServiceConfig;
import ru.otus.spring2021.homework_3.dao.AnswerDao;
import ru.otus.spring2021.homework_3.wrapper.MessageSourceWrapper;

import java.util.Scanner;

@Service
public class TestingService {
    private final AnswerDao answerDao;
    private final int passTest;
    private final MessageSourceWrapper msg;

    public TestingService(AnswerDao answerDao, ServiceConfig config, MessageSourceWrapper msg) {
        this.answerDao = answerDao;
        this.passTest = config.getPassTest();
        this.msg = msg;
    }

    public boolean checkTest() {
        int failCount = 0;
        Scanner scanner = new Scanner(System.in);
        try {
            for (String answer : answerDao.getAnswers().getDescription()) {
                String yourAnswer = scanner.nextLine();
                System.out.printf(msg.getMessage("string.answer"), answer, yourAnswer);
                if (!answer.equalsIgnoreCase(yourAnswer)) {
                    failCount++;
                }
            }
        } catch (Exception ignored) {
        }
        return failCount <= passTest;
    }
}
