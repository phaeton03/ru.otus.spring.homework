package ru.otus.spring.homework_30.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework_30.domain.Users;
import ru.otus.spring.homework_30.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    public Optional<Users> findUser(String userName) {
        return userRepository.findByName(userName);
    }
}