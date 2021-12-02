package ru.otus.spring.homework_13.exception;

public class AuthorNotFoundException extends HomeworkRootException {

    public AuthorNotFoundException(String message) {
        super(message);
    }
}