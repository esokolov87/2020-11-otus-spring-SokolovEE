package ru.sokolovee.serviceadvertisement.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sokolovee.serviceadvertisement.dtos.ContactDto;
import ru.sokolovee.serviceadvertisement.dtos.PersonDto;
import ru.sokolovee.serviceadvertisement.entities.Contact;
import ru.sokolovee.serviceadvertisement.mapping.ContactMapping;
import ru.sokolovee.serviceadvertisement.mapping.PersonMapping;
import ru.sokolovee.serviceadvertisement.repositories.ContactRepository;
import ru.sokolovee.serviceadvertisement.repositories.PersonRepository;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final ContactRepository contactRepository;
    private final PersonMapping personMapping;
    private final ContactMapping contactMapping;

    public PersonDto createPerson(PersonDto body) {
        return personMapping.personToPersonDto(personRepository.save(personMapping.personDtoToPerson(body)));
    }

    public ContactDto addContact(Long id, ContactDto body) {
        Contact contact = contactMapping.contactDtoToContact(body);
        contact.setPersonId(id);
        return contactMapping.contactToContactDto(contactRepository.save(contact));
    }
}
