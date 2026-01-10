package ru.sokolovee.serviceadvertisement.dtos;

import lombok.Data;

@Data
public class AdvertisementDto {
    private Long id;
    private String subject;
    private String body;
    private PersonDto owner;
    private String createDateTime;
    private Boolean isActive;
}
