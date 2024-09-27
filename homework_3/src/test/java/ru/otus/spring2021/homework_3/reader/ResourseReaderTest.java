package ru.otus.spring2021.homework_3.reader;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ResourseReaderTest {
    private final ResourseReader resourseReader = new ResourseReader();
    private static final int FILESIZE = 5;

    @Test
    public void isPathFileCorrect() {
        assertThrows(NullPointerException.class, () -> resourseReader.getFile("src/test/resources/testDaoHelper.csv"));
    }
    @Test
    @SneakyThrows
    public void isParsingCorrect() {
        assertThat(resourseReader.getFile("testDaoHelper.csv")).hasSize(FILESIZE);
    }
}