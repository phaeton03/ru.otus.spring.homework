package ru.otus.spring.homework_26.configuration.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReaderHelper<T> {
    public void clearList(List<T> readerList, Integer start, Integer end) {
        readerList.subList(start, end).clear();
    }
}