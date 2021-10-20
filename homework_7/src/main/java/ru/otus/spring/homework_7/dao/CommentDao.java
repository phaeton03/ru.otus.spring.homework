package ru.otus.spring.homework_7.dao;

import ru.otus.spring.homework_7.domain.Book;
import ru.otus.spring.homework_7.domain.Comment;

import java.util.List;


public interface CommentDao {
    List<Comment> getAllComments();
    void delete(Long id);
    void addComment(Comment comment);
}