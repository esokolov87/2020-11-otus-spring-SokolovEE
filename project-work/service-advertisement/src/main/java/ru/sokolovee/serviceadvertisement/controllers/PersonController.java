package ru.sokolovee.serviceadvertisement.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sokolovee.serviceadvertisement.dtos.ContactDto;
import ru.sokolovee.serviceadvertisement.dtos.PersonDto;
import ru.sokolovee.serviceadvertisement.services.PersonService;

@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping("/person")
    public PersonDto createPerson(@RequestBody PersonDto body) {
        return personService.createPerson(body);
    }

    @PostMapping("/person/{id}/contact")
    public ContactDto addContact(@PathVariable Long id, @RequestBody ContactDto body) {
        return personService.addContact(id, body);
    }

}
