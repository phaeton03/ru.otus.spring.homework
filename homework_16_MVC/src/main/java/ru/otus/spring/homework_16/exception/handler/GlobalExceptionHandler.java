package ru.otus.spring.homework_16.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import ru.otus.spring.homework_16.domain.Book;
import ru.otus.spring.homework_16.exception.AuthorNotFoundException;
import ru.otus.spring.homework_16.exception.BookNotFoundException;
import ru.otus.spring.homework_16.exception.GenreNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AuthorNotFoundException.class)
    public ModelAndView handleAuthorNotFoundException(AuthorNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("books");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(GenreNotFoundException.class)
    public ModelAndView handleGenreNotFoundException(GenreNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("books");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BookNotFoundException.class)
    public ModelAndView handleBookNotFoundException(BookNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("books");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }
}