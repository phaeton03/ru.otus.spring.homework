package ru.otus.spring2021.dao;


import ru.otus.spring2021.model.Quiz;

public interface QuestionaryDao {
    Quiz getQuestions() throws Exception;
}
