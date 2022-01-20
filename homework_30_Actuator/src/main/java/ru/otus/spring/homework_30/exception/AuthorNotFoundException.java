package ru.otus.spring.homework_30.exception;

import ru.otus.spring.homework_30.exception.root.HomeworkRootException;

public class AuthorNotFoundException extends HomeworkRootException {

    public AuthorNotFoundException(String message) {
        super(message);
    }
}