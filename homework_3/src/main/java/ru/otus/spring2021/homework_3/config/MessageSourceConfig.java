package ru.otus.spring2021.homework_3.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "message")
@Configuration
@Data
public class MessageSourceConfig {
    private String locale;
}
