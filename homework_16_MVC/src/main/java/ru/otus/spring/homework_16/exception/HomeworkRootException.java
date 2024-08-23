package ru.otus.spring.homework_16.exception;

public class HomeworkRootException extends RuntimeException {
    public HomeworkRootException() {
    }

    public HomeworkRootException(String message) {
        super(message);
    }
}