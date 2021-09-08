package ru.otus.spring2021.dao;//package ru.otus.spring.dao;


import ru.otus.spring2021.model.Quiz;

public interface QuestionaryDAO {
    public Quiz getQuestions() throws Exception;
}
