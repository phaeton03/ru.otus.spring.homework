package ru.otus.spring2021.homework_3.service.exception;

public class IncorrectNameOrSurname extends RuntimeException {
    private static final String exception = "Incorrect name or surname. It should contains only letters";
    public IncorrectNameOrSurname() {
        super(exception);
    }
}
