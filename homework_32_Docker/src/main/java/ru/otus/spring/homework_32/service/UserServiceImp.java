package ru.otus.spring.homework_32.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework_32.domain.Users;
import ru.otus.spring.homework_32.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    public Optional<Users> findUser(String userName) {
        return userRepository.findByName(userName);
    }
}