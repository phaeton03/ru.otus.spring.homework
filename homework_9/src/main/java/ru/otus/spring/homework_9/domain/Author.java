package ru.otus.spring.homework_9.domain;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = "books")
@ToString(exclude = "books")
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author", orphanRemoval = true)
    private Set<Book> books;
}