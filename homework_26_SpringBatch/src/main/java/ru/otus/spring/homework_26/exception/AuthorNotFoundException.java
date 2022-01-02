package ru.otus.spring.homework_26.exception;

import ru.otus.spring.homework_26.exception.root.HomeworkRootException;

public class AuthorNotFoundException extends HomeworkRootException {
    public AuthorNotFoundException() {
        super();
    }

    public AuthorNotFoundException(String message) {
        super(message);
    }
}