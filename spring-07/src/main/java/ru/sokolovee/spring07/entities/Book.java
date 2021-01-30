package ru.sokolovee.spring07.entities;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Book")
@NamedEntityGraph(name = "book-entity-graph", attributeNodes = {@NamedAttributeNode("author"), @NamedAttributeNode("genre"), @NamedAttributeNode("comments")})

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENERATOR_BOOK_SEQ")
    @SequenceGenerator(name = "GENERATOR_BOOK_SEQ", sequenceName = "BOOK_SEQ", initialValue = 5, allocationSize = 1)
    private long id;

    private String name;

    @Fetch(FetchMode.JOIN)
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @Fetch(FetchMode.JOIN)
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private Genre genre;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private List<Comment> comments;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                ", comments=" + comments +
                '}';
    }
}
