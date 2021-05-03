package ru.sokolovee.serviceadvertisement.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.sokolovee.serviceadvertisement.dtos.PersonDto;
import ru.sokolovee.serviceadvertisement.entities.Person;

@Mapper
public interface PersonMapping {

    @Mapping(source = "person.user.username", target = "username")
    PersonDto personToPersonDto(Person person);

    @Mapping(source = "person.user.username", target = "username")
    Person personDtoToPerson(PersonDto dto);
}
