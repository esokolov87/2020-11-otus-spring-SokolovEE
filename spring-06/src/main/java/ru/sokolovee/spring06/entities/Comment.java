package ru.sokolovee.spring06.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENERATOR_COMMENT_SEQ")
    @SequenceGenerator(name = "GENERATOR_COMMENT_SEQ", sequenceName = "COMMENT_SEQ", initialValue = 5, allocationSize = 1)
    private Long id;
    private Long book_id;
    private String comment;
}
