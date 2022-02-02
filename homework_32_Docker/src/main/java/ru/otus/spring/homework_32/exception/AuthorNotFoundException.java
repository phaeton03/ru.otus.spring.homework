package ru.otus.spring.homework_32.exception;

import ru.otus.spring.homework_32.exception.root.HomeworkRootException;

public class AuthorNotFoundException extends HomeworkRootException {

    public AuthorNotFoundException(String message) {
        super(message);
    }
}