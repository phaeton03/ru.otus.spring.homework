package ru.otus.spring2021.dao.helper;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DaoHelperTest {

    @Test
    void checkFile() throws Exception {
        assertThat(DaoHelper.getFile("testDaoHelper.csv").size()).isEqualTo(5);
    }
}