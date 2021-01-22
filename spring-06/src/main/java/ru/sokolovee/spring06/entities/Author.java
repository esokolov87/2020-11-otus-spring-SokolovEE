package ru.sokolovee.spring06.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENERATOR_AUTHOR_SEQ")
    @SequenceGenerator(name = "GENERATOR_AUTHOR_SEQ", sequenceName = "AUTHOR_SEQ", initialValue = 5, allocationSize = 1)
    private long id;
    private String name;
}
