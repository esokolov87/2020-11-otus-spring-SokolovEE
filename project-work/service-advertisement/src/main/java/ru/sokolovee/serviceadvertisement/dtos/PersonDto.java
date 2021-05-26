package ru.sokolovee.serviceadvertisement.dtos;

import lombok.Data;

import java.util.List;

@Data
public class PersonDto {
    private Long id;
    private String username;
    private String password;
    private String fullName;
    private String authority;
    private List<ContactDto> contacts;
    private Boolean enabled;
}
