package ru.sokolovee.serviceadvertisement.entities;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class User {
    private String username;
    private String password;
    private Boolean enabled;
}
