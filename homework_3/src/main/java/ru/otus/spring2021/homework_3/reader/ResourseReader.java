package ru.otus.spring2021.homework_3.reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ResourseReader {
    public List<String> getFile(String path) throws Exception {
        ClassLoader classLoader = ResourseReader.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(path)).getFile());
        Reader in = new FileReader(file);
        List<String> content = new ArrayList<>();
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
        for (CSVRecord record : records) {
            content.add(record.get(0));
        }
        return content;
    }
}
