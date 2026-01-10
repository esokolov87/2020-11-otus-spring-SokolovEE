package ru.sokolovee.serviceadvertisement.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.sokolovee.serviceadvertisement.dtos.ContactDto;
import ru.sokolovee.serviceadvertisement.dtos.PersonDto;
import ru.sokolovee.serviceadvertisement.entities.Authority;
import ru.sokolovee.serviceadvertisement.entities.Contact;
import ru.sokolovee.serviceadvertisement.entities.User;

@Mapper(componentModel = "spring")
public interface AuthorityMapping {

    @Mappings(value = {
            @Mapping(source = "authority.authority", target = "authority")
    })
    PersonDto entityToDto(Authority authority);

    @Mappings(value = {
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "authority", target = "authority")
    })
    Authority dtoToEntity(PersonDto dto);
}
