package ru.otus.spring.homework_13.exception;

public class BookNotFoundException extends HomeworkRootException {
    public BookNotFoundException(String message) {
        super(message);
    }
}