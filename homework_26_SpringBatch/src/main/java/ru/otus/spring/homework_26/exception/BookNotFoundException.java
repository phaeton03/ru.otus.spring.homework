package ru.otus.spring.homework_26.exception;

import ru.otus.spring.homework_26.exception.root.HomeworkRootException;

public class BookNotFoundException extends HomeworkRootException {
    public BookNotFoundException() { super(); }

    public BookNotFoundException(String message) {
        super(message);
    }
}