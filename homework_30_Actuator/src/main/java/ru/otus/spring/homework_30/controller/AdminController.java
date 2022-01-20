package ru.otus.spring.homework_30.controller;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.homework_30.service.AdminService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final MeterRegistry registry;

    @GetMapping("/")
    public String getMenu() {
        registry.counter("counter.menu").increment();
        return "admin";
    }

    @PostMapping("/delete-book/{bookId}")
    public String deleteBook(@RequestParam(value = "bookId") Long bookId, Model model) {
        model.addAttribute("bookId", bookId);
        adminService.deleteBook(bookId);
        return "admin";
    }

    @PostMapping("/delete-comment/{commentId}")
    public String deleteComment(@RequestParam(value = "commentId") Long commentId, Model model) {
        model.addAttribute("commentId", commentId);
        adminService.deleteComment(commentId);
        return "admin";
    }

    @PostMapping("/edit-comment")
    public String editComment(@RequestParam Long commentId, @RequestParam String message) {
        adminService.editComment(commentId, message);
        return "admin";
    }
}