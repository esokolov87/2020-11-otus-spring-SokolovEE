package ru.sokolovee.serviceadvertisement.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.sokolovee.serviceadvertisement.dtos.PersonDto;
import ru.sokolovee.serviceadvertisement.entities.Authority;
import ru.sokolovee.serviceadvertisement.entities.Person;
import ru.sokolovee.serviceadvertisement.entities.User;

@Mapper(componentModel = "spring", uses = {ContactMapping.class})
public interface PersonMapping {

    PersonDto entityToDto(Person person);

    Person dtoToEntity(PersonDto dto);
}
