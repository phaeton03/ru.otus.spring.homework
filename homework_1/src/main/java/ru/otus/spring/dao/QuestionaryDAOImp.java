package ru.otus.spring.dao;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import ru.otus.spring.model.Quiz;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class QuestionaryDAOImp implements QuestionaryDAO {
    public Quiz getQuestions() throws Exception {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("QuestionsToStudents.csv").getFile());
        Reader in = new FileReader(file);
        List<String> questions = new ArrayList<>();
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
        for (CSVRecord record : records) {
            questions.add(record.get(0));
        }
        return new Quiz(questions);
    }
}
