package ru.otus.spring.homework_32.service;

import ru.otus.spring.homework_32.domain.Users;

import java.util.Optional;

public interface UserService {
    Optional<Users> findUser(String userName);
}