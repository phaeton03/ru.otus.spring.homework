package ru.otus.spring2021.homework_3.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring2021.homework_3.dao.*;
import ru.otus.spring2021.homework_3.service.*;

@ConfigurationProperties(prefix = "pass")
@Configuration
@Data
public class ServiceConfig {
    private int passTest;
}

