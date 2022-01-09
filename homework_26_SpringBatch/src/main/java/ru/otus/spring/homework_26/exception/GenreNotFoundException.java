package ru.otus.spring.homework_26.exception;

import ru.otus.spring.homework_26.exception.root.HomeworkRootException;

public class GenreNotFoundException extends HomeworkRootException {
    public GenreNotFoundException() {
        super();
    }

    public GenreNotFoundException(String message) {
        super(message);
    }
}