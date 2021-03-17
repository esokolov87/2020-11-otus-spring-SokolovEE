package ru.sokolovee.spring13.entities;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENERATOR_COMMENT_SEQ")
    @SequenceGenerator(name = "GENERATOR_COMMENT_SEQ", sequenceName = "COMMENT_SEQ", initialValue = 5, allocationSize = 1)
    private Long id;
    @Fetch(FetchMode.JOIN)
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;
    private String comment;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", book=" + book.getName() +
                ", comment='" + comment + '\'' +
                '}';
    }

    public Comment(Long id) {
        this.id = id;
    }
}

