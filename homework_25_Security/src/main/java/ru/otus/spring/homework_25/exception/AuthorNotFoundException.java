package ru.otus.spring.homework_25.exception;

import ru.otus.spring.homework_25.exception.root.HomeworkRootException;

public class AuthorNotFoundException extends HomeworkRootException {

    public AuthorNotFoundException(String message) {
        super(message);
    }
}