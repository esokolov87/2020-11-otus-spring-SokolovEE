package ru.sokolovee.serviceadvertisement.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENERATOR_CONTACT_SEQ")
    @SequenceGenerator(name = "GENERATOR_CONTACT_SEQ", sequenceName = "CONTACT_SEQ", initialValue = 1, allocationSize = 1)
    private Long id;
    private String type;
    private String content;
    private String comment;
    private Long personId;
}
