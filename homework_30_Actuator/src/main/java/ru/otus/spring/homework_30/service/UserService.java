package ru.otus.spring.homework_30.service;

import ru.otus.spring.homework_30.domain.Users;

import java.util.Optional;

public interface UserService {
    Optional<Users> findUser(String userName);
}