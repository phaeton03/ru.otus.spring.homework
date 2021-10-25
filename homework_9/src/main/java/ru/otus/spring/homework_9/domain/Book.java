package ru.otus.spring.homework_9.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "books")
@NamedEntityGraph(name = "book-graph",
attributeNodes = {@NamedAttributeNode("author"), @NamedAttributeNode("genre")})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private List<Comment> comments;
}