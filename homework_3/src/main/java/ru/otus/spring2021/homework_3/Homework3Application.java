package ru.otus.spring2021.homework_3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import ru.otus.spring2021.homework_3.service.ExamService;

import java.util.Locale;

@SpringBootApplication
public class Homework3Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Homework3Application.class, args);
        ExamService examService = ctx.getBean(ExamService.class);
        examService.passExam();
    }
}
