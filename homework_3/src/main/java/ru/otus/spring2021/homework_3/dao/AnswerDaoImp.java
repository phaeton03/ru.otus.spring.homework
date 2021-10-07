package ru.otus.spring2021.homework_3.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring2021.homework_3.config.MessageSourceConfig;
import ru.otus.spring2021.homework_3.reader.ResourseReader;
import ru.otus.spring2021.homework_3.model.Answer;
import ru.otus.spring2021.homework_3.wrapper.MessageSourceWrapper;

@Repository
@AllArgsConstructor
public class AnswerDaoImp implements AnswerDao {
    private final ResourseReader resourseReader;
    private final MessageSourceWrapper msg;

    @Override
    public Answer getAnswers() throws Exception {
        return new Answer(resourseReader.getFile(msg.getMessage("filename.answers")));
    }
}
