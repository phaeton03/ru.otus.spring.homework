package ru.otus.spring.homework_32.controller;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class SecurityController {
    private final MeterRegistry registry;

    @GetMapping("/403")
    public String error() {
        registry.counter("counter.error403").increment();
        return "error";
    }


}