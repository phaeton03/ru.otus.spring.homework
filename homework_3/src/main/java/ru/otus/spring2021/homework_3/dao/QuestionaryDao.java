package ru.otus.spring2021.homework_3.dao;


import ru.otus.spring2021.homework_3.model.Quiz;

public interface QuestionaryDao {
    Quiz getQuestions() throws Exception;
}
