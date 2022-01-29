package ru.otus.spring.homework_32.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import ru.otus.spring.homework_32.exception.AuthorNotFoundException;
import ru.otus.spring.homework_32.exception.BookNotFoundException;
import ru.otus.spring.homework_32.exception.CommentNotFoundException;
import ru.otus.spring.homework_32.exception.GenreNotFoundException;

import java.util.Optional;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({GenreNotFoundException.class, BookNotFoundException.class, AuthorNotFoundException.class})
    public ModelAndView handleNotFoundException(GenreNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("books");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({CommentNotFoundException.class})
    public ModelAndView handleCommentNotFoundException(GenreNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("comments");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> accessError() {
        return ResponseEntity.of(Optional.of("Ошибка авторизации"));
    }
}