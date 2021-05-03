package ru.sokolovee.serviceadvertisement.dtos;

import lombok.Data;

import java.util.List;

@Data
public class PersonDto {
    private Long id;
    private String username;
    private String password;
    private String fullName;
    private List<ContactDto> contacts;
}
