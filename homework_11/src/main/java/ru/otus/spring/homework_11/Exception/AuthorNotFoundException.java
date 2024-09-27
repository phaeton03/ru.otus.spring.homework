package ru.otus.spring.homework_11.Exception;

public class AuthorNotFoundException extends HomeworkRootException {

    public AuthorNotFoundException(String message) {
        super(message);
    }
}