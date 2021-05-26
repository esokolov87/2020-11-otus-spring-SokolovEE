package ru.sokolovee.serviceadvertisement.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.sokolovee.serviceadvertisement.dtos.ContactDto;
import ru.sokolovee.serviceadvertisement.dtos.PersonDto;
import ru.sokolovee.serviceadvertisement.entities.Contact;
import ru.sokolovee.serviceadvertisement.entities.Person;

@Mapper(componentModel = "spring")
public interface ContactMapping {

    ContactDto entityToDto(Contact contact);

    Contact dtoToEntity(ContactDto dto);
}
