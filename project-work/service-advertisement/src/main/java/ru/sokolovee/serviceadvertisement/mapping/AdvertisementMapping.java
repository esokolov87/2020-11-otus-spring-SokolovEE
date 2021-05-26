package ru.sokolovee.serviceadvertisement.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.sokolovee.serviceadvertisement.dtos.AdvertisementDto;
import ru.sokolovee.serviceadvertisement.dtos.PersonDto;
import ru.sokolovee.serviceadvertisement.entities.Advertisement;
import ru.sokolovee.serviceadvertisement.entities.Person;

@Mapper(componentModel = "spring", uses = {PersonMapping.class})
public interface AdvertisementMapping {

    AdvertisementDto advToAdvDto(Advertisement advertisement);

    Advertisement advDtoToAdv(AdvertisementDto dto);
}
