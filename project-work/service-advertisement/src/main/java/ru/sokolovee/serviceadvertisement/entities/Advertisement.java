package ru.sokolovee.serviceadvertisement.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENERATOR_ADVERTISEMENT_SEQ")
    @SequenceGenerator(name = "GENERATOR_ADVERTISEMENT_SEQ", sequenceName = "ADVERTISEMENT_SEQ", initialValue = 1, allocationSize = 1)
    private Long id;
    private String subject;
    private String body;
    @ManyToOne(targetEntity = Person.class)
    @JoinColumn(name = "OWNER_ID", referencedColumnName = "ID")
    private Person owner;
    private LocalDateTime createDateTime;
    private Boolean isActive;
}
