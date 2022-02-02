package ru.otus.spring.homework_32.exception;

import ru.otus.spring.homework_32.exception.root.HomeworkRootException;

public class CommentNotFoundException extends HomeworkRootException {
    public CommentNotFoundException(String message) {
        super(message);
    }
}