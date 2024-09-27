package ru.otus.spring.homework_13.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework_13.domain.Author;
import ru.otus.spring.homework_13.exception.AuthorNotFoundException;
import ru.otus.spring.homework_13.repository.AuthorRepository;
import ru.otus.spring.homework_13.repository.BookRepository;

@Service
@RequiredArgsConstructor
public class AuthorServiceImp implements AuthorService {
    private static final String OFFSET = "\n\n----------------------------\n\n";

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    public void rename(String authorId, String newAuthorName) {
        Author author = getAuthor(authorId,"Author with id = %s does not exist");
        author.setName(newAuthorName);
        authorRepository.save(author);
    }

    @Override
    public void writeBiography(String authorId, String history) {
        Author author = getAuthor(authorId,"Author with id = %s does not exist");
        author.setBiography(history);
        authorRepository.save(author);

    }

    @Override
    public String getAuthor(String authorId) {
        return OFFSET + getAuthor(authorId,"Author with id = %s does not exist");
    }

    private Author getAuthor(String authorId, String msg) {
        return authorRepository
                .findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(String.format(msg, authorId)));
    }

}