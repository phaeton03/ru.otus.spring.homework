package ru.otus.spring.homework_25.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.homework_25.domain.Book;
import ru.otus.spring.homework_25.domain.Comment;
import ru.otus.spring.homework_25.service.BookService;
import ru.otus.spring.homework_25.service.BookServiceImp;
import ru.otus.spring.homework_25.service.CommentService;
import ru.otus.spring.homework_25.service.CommentServiceImp;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final CommentService commentService;

    @GetMapping("/")
    public String findAllBooks(Model model) {
        List<Book> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/comments")
    public String findBooksComments(@RequestParam(value = "bookId") long bookId, Model model) {
        List<Comment> comments = commentService.getAllCommentsByBook(bookId);
        model.addAttribute("bookId", bookId);
        model.addAttribute("comments", comments);
        return "comments";
    }

    @GetMapping("/{genre}")
    public String findBooksByGenre(@RequestParam(value = "genre") String genre, Model model) {
        List<Book> books = bookService.findBooksByGenre(genre);
        System.out.println(books.size());
        model.addAttribute("books", books);
        return "books";
    }


    @GetMapping("/{author}")
    public String findBooksByAuthor(@RequestParam(value = "author") String author, Model model) {
        List<Book> books = bookService.findBooksByAuthor(author);
        System.out.println(books.size());
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/{id}")
    public String findBooksById(@RequestParam(value = "id") long id, Model model) {
        Book book = bookService.findBook(id);
        model.addAttribute("books", List.of(book));
        return "books";
    }

    @PostMapping("/comments")
    public String publishComment(@RequestParam(value = "bookId") long bookId, @RequestParam String msg, Model model) {
        commentService.publishComment(msg, bookId);
        model.addAttribute("bookId", bookId);
        model.addAttribute("comments", bookService.findBook(bookId).getComments());
        return "comments";
    }
}