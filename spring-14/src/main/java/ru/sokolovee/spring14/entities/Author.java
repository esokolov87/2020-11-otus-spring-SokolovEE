package ru.sokolovee.spring14.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENERATOR_AUTHOR_SEQ")
    @SequenceGenerator(name = "GENERATOR_AUTHOR_SEQ", sequenceName = "AUTHOR_SEQ", initialValue = 1, allocationSize = 1)
    private long id;
    private String name;

    public Author(long id) {
        this.id = id;
    }
}
