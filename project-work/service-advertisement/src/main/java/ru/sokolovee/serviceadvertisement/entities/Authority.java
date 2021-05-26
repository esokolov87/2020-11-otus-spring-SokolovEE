package ru.sokolovee.serviceadvertisement.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "authorities")
@AllArgsConstructor
@NoArgsConstructor
public class Authority {
    @Id
    private String username;
    private String authority;
}
