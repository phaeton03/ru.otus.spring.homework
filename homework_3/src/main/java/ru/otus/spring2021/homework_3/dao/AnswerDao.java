package ru.otus.spring2021.homework_3.dao;


import ru.otus.spring2021.homework_3.model.Answer;

public interface AnswerDao {
    Answer getAnswers() throws Exception;
}
