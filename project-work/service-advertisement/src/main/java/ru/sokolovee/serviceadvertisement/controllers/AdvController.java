package ru.sokolovee.serviceadvertisement.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sokolovee.serviceadvertisement.dtos.AdvertisementDto;
import ru.sokolovee.serviceadvertisement.entities.Advertisement;
import ru.sokolovee.serviceadvertisement.services.AdvertisementService;

@RestController
@RequiredArgsConstructor
public class AdvController {

    private final AdvertisementService advServise;

    @PostMapping("/adv")
    public AdvertisementDto inserAdvertisement(@RequestBody AdvertisementDto body) {
        return advServise.insert(body);
    }

    @PatchMapping("/adv/{id}")
    public AdvertisementDto updateAdvertisement(@PathVariable Long id, @RequestBody AdvertisementDto body) {
        return advServise.update(id, body);
    }

    @DeleteMapping("/adv/{id}")
    public void deleteAdvertisement(@PathVariable Long id) {
        advServise.deleteById(id);
    }

}
