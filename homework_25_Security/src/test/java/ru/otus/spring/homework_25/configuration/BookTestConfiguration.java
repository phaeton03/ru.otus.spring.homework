package ru.otus.spring.homework_25.configuration;

import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandler;
import ru.otus.spring.homework_25.repository.UserRepository;
import ru.otus.spring.homework_25.security.configuration.SecurityConfiguration;
import ru.otus.spring.homework_25.security.handler.CustomAccessDeniedHandler;
import ru.otus.spring.homework_25.security.service.CustomUserDetailsService;

@TestConfiguration
@Import(SecurityConfiguration.class)
//@EnableJpaAuditing
//@EnableJpaRepositories("ru.otus.spring.homework_25.repository")
public class BookTestConfiguration {

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return Mockito.mock(CustomAccessDeniedHandler.class);
    }

    @Bean
    public CustomUserDetailsService customUserDetailService() {
        return Mockito.mock(CustomUserDetailsService.class);
    }
}