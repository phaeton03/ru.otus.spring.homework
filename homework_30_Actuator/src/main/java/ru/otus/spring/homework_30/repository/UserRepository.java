package ru.otus.spring.homework_30.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework_30.domain.Users;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByName(String name);
}