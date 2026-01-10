package ru.sokolovee.serviceadvertisement.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.sokolovee.serviceadvertisement.dtos.PersonDto;
import ru.sokolovee.serviceadvertisement.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapping {

    @Mappings(value = {
            @Mapping(source = "user.username", target = "username"),
            @Mapping(source = "user.password", target = "password"),
            @Mapping(source = "user.enabled", target = "enabled")
    })
    PersonDto entityToDto(User user);

    @Mappings(value = {
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "enabled", target = "enabled", defaultValue = "true")
    })
    User dtoToEntity(PersonDto dto);
}
