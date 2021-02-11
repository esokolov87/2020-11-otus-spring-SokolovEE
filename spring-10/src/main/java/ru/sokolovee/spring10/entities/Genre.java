package ru.sokolovee.spring10.entities;

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
    @SequenceGenerator(name = "GENERATOR_GENRE_SEQ", sequenceName = "GENRE_SEQ", initialValue = 5, allocationSize = 1)
    private Long id;
    private String name;

    public Genre(Long id) {
        this.id = id;
    }
}
