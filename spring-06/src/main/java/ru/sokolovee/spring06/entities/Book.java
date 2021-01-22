package ru.sokolovee.spring06.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Book")
@NamedEntityGraph(name = "book-entity-graph", attributeNodes = {@NamedAttributeNode("author"), @NamedAttributeNode("genre"), @NamedAttributeNode("comments")})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENERATOR_BOOK_SEQ")
    @SequenceGenerator(name = "GENERATOR_BOOK_SEQ", sequenceName = "BOOK_SEQ", initialValue = 5, allocationSize = 1)
    private long id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private Genre genre;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
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
