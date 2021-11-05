package ru.otus.spring.homework_11.Exception;

public class BookNotFoundException extends HomeworkRootException {
    public BookNotFoundException(String message) {
        super(message);
    }
}