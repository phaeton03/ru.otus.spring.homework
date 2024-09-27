package ru.otus.spring.homework_30.exception.root;

public class HomeworkRootException extends RuntimeException {
    public HomeworkRootException() {
    }

    public HomeworkRootException(String message) {
        super(message);
    }
}