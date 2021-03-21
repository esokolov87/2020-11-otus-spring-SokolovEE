package ru.sokolovee.spring14.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENERATOR_GENRE_SEQ")
    @SequenceGenerator(name = "GENERATOR_GENRE_SEQ", sequenceName = "GENRE_SEQ", initialValue = 1, allocationSize = 1)
    private long id;
    private String name;

    public Genre(long id) {
        this.id = id;
    }
}
