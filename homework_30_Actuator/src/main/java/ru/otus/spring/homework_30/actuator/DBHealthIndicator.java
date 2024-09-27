package ru.otus.spring.homework_30.actuator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework_30.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class DBHealthIndicator implements HealthIndicator {
    private final UserRepository userRepository;

    @Override
    public Health health() {
        if (checkDBHealth()) {
            return Health.down()
                    .status(Status.DOWN)
                    .withDetail("message", "Check your database")
                    .build();
        } else {
            return Health.up().withDetail("message", "Everything is ok").build();
        }
    }

    private Boolean checkDBHealth() {
        return userRepository.count() < 2;
    }
}