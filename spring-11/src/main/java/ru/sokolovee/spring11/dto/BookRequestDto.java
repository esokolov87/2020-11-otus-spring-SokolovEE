package ru.sokolovee.spring11.dto;

import lombok.Data;

@Data
public class BookRequestDto {
    private String name;
    private Long authorid;
    private Long genreid;
}
