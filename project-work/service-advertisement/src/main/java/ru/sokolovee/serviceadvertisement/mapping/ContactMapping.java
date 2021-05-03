package ru.sokolovee.serviceadvertisement.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.sokolovee.serviceadvertisement.dtos.ContactDto;
import ru.sokolovee.serviceadvertisement.dtos.PersonDto;
import ru.sokolovee.serviceadvertisement.entities.Contact;
import ru.sokolovee.serviceadvertisement.entities.Person;

@Mapper
public interface ContactMapping {

    ContactDto contactToContactDto(Contact contact);

    Contact contactDtoToContact(ContactDto dto);
}
