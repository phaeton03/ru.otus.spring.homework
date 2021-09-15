package ru.otus.spring2021.dao;

import lombok.AllArgsConstructor;
import ru.otus.spring2021.dao.helper.DaoHelper;
import ru.otus.spring2021.model.Answer;

@AllArgsConstructor
public class AnswerDaoImp implements AnswerDao {
    String path;

    @Override
    public Answer getAnswers() throws Exception {
        return new Answer(DaoHelper.getFile(path));
    }
}
