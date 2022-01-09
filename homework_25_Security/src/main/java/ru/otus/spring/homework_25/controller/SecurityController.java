package ru.otus.spring.homework_25.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class SecurityController {
    @GetMapping("/403")
    public String error() {
        return "error";
    }


}