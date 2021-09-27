package ru.otus.spring2021.dao;

import ru.otus.spring2021.model.Answer;

public interface AnswerDao {
    Answer getAnswers() throws Exception;
}
