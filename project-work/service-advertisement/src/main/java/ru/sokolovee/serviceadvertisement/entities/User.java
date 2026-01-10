package ru.sokolovee.serviceadvertisement.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "USERS")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String username;
    private String password;
    @Column(nullable = false, columnDefinition = "VARCHAR(1)")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean enabled;
}
