package ru.sokolovee.serviceadvertisement.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sokolovee.serviceadvertisement.dtos.AdvertisementDto;
import ru.sokolovee.serviceadvertisement.mapping.AdvertisementMapping;
import ru.sokolovee.serviceadvertisement.repositories.AdvertisementRepository;

@Service
@RequiredArgsConstructor
public class AdvertisementService {

    private final AdvertisementRepository advRepository;
    private final AdvertisementMapping advMapping;

    public AdvertisementDto insert(AdvertisementDto adv) {
        return advMapping.advToAdvDto(advRepository.save(advMapping.advDtoToAdv(adv)));
    }

    public AdvertisementDto update(Long id, AdvertisementDto adv) {
        adv.setId(id);
        return advMapping.advToAdvDto(advRepository.save(advMapping.advDtoToAdv(adv)));
    }

    public void deleteById(Long id) {
        advRepository.deleteById(id);
    }
}
