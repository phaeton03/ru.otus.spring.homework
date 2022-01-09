package ru.otus.spring.homework_25.service;

import ru.otus.spring.homework_25.domain.Users;

import java.util.Optional;

public interface UserService {
    Optional<Users> findUser(String userName);
}