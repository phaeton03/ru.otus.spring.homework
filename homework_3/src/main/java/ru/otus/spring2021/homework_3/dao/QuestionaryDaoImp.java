package ru.otus.spring2021.homework_3.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring2021.homework_3.reader.ResourseReader;
import ru.otus.spring2021.homework_3.model.Quiz;
import ru.otus.spring2021.homework_3.wrapper.MessageSourceWrapper;

@Repository
@AllArgsConstructor
public class QuestionaryDaoImp implements QuestionaryDao {
    private final ResourseReader resourseReader;
    private final MessageSourceWrapper msg;

    public Quiz getQuestions() throws Exception {
        return new Quiz(resourseReader.getFile(msg.getMessage("filename.questions")));
    }
}
