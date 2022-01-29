package ru.otus.spring.homework_32.configuration;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import ru.otus.spring.homework_32.security.configuration.SecurityConfiguration;
import ru.otus.spring.homework_32.security.handler.CustomAccessDeniedHandler;
import ru.otus.spring.homework_32.security.service.CustomUserDetailsService;

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