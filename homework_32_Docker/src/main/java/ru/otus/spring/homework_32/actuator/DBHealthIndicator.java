package ru.otus.spring.homework_32.actuator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework_32.repository.UserRepository;

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
        Boolean checkDBHealth = false;
        try {
            checkDBHealth = userRepository.count() < 2;
        } catch (Exception e) {
        } finally {
            return checkDBHealth;
        }
    }
}