package ru.otus.spring.dao;//package ru.otus.spring.dao;


import ru.otus.spring.model.Quiz;

public interface QuestionaryDAO {
    public Quiz getQuestions() throws Exception;
}
