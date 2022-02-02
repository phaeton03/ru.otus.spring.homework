package ru.otus.spring.homework_32.controller;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.homework_32.domain.Book;
import ru.otus.spring.homework_32.domain.Comment;
import ru.otus.spring.homework_32.service.BookService;
import ru.otus.spring.homework_32.service.CommentService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final CommentService commentService;
    private final MeterRegistry registry;

    @GetMapping("/")
    @Timed(value = "allBooks.time", description = "Time taken to find book by id")
    public String findAllBooks(Model model) {
        registry.counter("counter.allBooks").increment();
        List<Book> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/comments")
    @Timed(value = "comments.time", description = "Time taken to find book by id")
    public String findBooksComments(@RequestParam(value = "bookId") long bookId, Model model) {
        List<Comment> comments = commentService.getAllCommentsByBook(bookId);
        model.addAttribute("bookId", bookId);
        model.addAttribute("comments", comments);
        return "comments";
    }

    @GetMapping("/{genre}")
    @Timed(value = "findBookByGenre.time", description = "Time taken to find book by genre")
    public String findBooksByGenre(@RequestParam(value = "genre") String genre, Model model) {
        List<Book> books = bookService.findBooksByGenre(genre);
        model.addAttribute("books", books);
        return "books";
    }


    @GetMapping("/{author}")
    @Timed(value = "findBookByAuthor.time", description = "Time taken to find book by author")
    public String findBooksByAuthor(@RequestParam(value = "author") String author, Model model) {
        List<Book> books = bookService.findBooksByAuthor(author);
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/{id}")
    @Timed(value = "findBookById.time", description = "Time taken to find book by id")
    public String findBooksById(@RequestParam(value = "id") long id, Model model) {
        Book book = bookService.findBook(id);
        model.addAttribute("books", List.of(book));
        return "books";
    }

    @PostMapping("/comments")
    @Timed(value = "publish.comment.time", description = "Time taken to publish comments")
    public String publishComment(@RequestParam(value = "bookId") long bookId, @RequestParam String msg, Model model) {
        commentService.publishComment(msg, bookId);
        model.addAttribute("bookId", bookId);
        model.addAttribute("comments", bookService.findBook(bookId).getComments());
        return "comments";
    }
}