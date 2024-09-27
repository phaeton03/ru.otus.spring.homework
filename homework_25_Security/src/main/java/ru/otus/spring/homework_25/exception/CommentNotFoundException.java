package ru.otus.spring.homework_25.exception;

import ru.otus.spring.homework_25.exception.root.HomeworkRootException;

public class CommentNotFoundException extends HomeworkRootException {
    public CommentNotFoundException(String message) {
        super(message);
    }
}