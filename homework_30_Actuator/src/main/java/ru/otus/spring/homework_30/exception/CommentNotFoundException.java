package ru.otus.spring.homework_30.exception;

import ru.otus.spring.homework_30.exception.root.HomeworkRootException;

public class CommentNotFoundException extends HomeworkRootException {
    public CommentNotFoundException(String message) {
        super(message);
    }
}