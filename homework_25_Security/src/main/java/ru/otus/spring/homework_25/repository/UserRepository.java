package ru.otus.spring.homework_25.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework_25.domain.Users;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByName(String name);
}