package ru.otus.spring2021.dao;

import lombok.AllArgsConstructor;
import ru.otus.spring2021.dao.helper.DaoHelper;
import ru.otus.spring2021.model.Quiz;

@AllArgsConstructor
public class QuestionaryDaoImp implements QuestionaryDao {
    private String path;

    public Quiz getQuestions() throws Exception {
        return new Quiz(DaoHelper.getFile(path));
    }
}
