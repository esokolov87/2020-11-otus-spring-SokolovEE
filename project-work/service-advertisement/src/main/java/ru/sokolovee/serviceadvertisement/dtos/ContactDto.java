package ru.sokolovee.serviceadvertisement.dtos;

import lombok.Data;

@Data
public class ContactDto {
    private Long id;
    private String type;
    private String content;
    private String comment;
}
