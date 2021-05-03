package ru.sokolovee.serviceadvertisement.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.sokolovee.serviceadvertisement.dtos.AdvertisementDto;
import ru.sokolovee.serviceadvertisement.dtos.PersonDto;
import ru.sokolovee.serviceadvertisement.entities.Advertisement;
import ru.sokolovee.serviceadvertisement.entities.Person;

@Mapper(componentModel = "spring")
public interface AdvertisementMapping {

    @Mapping(source = "person.user.name", target = "username")
    AdvertisementDto advToAdvDto(Advertisement advertisement);

    @Mapping(source = "person.user.name", target = "username")
    Advertisement advDtoToAdv(AdvertisementDto dto);
}
