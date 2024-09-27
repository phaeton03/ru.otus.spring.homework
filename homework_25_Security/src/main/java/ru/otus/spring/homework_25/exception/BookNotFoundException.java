package ru.otus.spring.homework_25.exception;

import ru.otus.spring.homework_25.exception.root.HomeworkRootException;

public class BookNotFoundException extends HomeworkRootException {
    public BookNotFoundException(String message) {
        super(message);
    }
}