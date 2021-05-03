package ru.sokolovee.serviceadvertisement.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENERATOR_PERSON_SEQ")
    @SequenceGenerator(name = "GENERATOR_PERSON_SEQ", sequenceName = "PERSON_SEQ", initialValue = 1, allocationSize = 1)
    private Long id;
    @JoinColumn(name = "USERNAME", referencedColumnName = "USERNAME")
    private User user;
    private String fullName;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(referencedColumnName = "PERSON_ID")
    private List<Contact> contacts;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Advertisement> advertisements;
}
