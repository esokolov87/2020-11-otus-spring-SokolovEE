package ru.sokolovee.serviceadvertisement.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sokolovee.serviceadvertisement.dtos.ContactDto;
import ru.sokolovee.serviceadvertisement.dtos.PersonDto;
import ru.sokolovee.serviceadvertisement.entities.Authority;
import ru.sokolovee.serviceadvertisement.entities.Contact;
import ru.sokolovee.serviceadvertisement.entities.User;
import ru.sokolovee.serviceadvertisement.mapping.AuthorityMapping;
import ru.sokolovee.serviceadvertisement.mapping.ContactMapping;
import ru.sokolovee.serviceadvertisement.mapping.PersonMapping;
import ru.sokolovee.serviceadvertisement.mapping.UserMapping;
import ru.sokolovee.serviceadvertisement.repositories.AuthorityRepository;
import ru.sokolovee.serviceadvertisement.repositories.ContactRepository;
import ru.sokolovee.serviceadvertisement.repositories.PersonRepository;
import ru.sokolovee.serviceadvertisement.repositories.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class PersonService {

    private final PersonRepository personRepository;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final ContactRepository contactRepository;
    private final PersonMapping personMapping;
    private final UserMapping userMapping;
    private final AuthorityMapping authorityMapping;
    private final ContactMapping contactMapping;

    public PersonDto createPerson(PersonDto person) {
        userRepository.save(userMapping.dtoToEntity(person));
        authorityRepository.save(authorityMapping.dtoToEntity(person));
        return personMapping.entityToDto(personRepository.save(personMapping.dtoToEntity(person)));
    }

    public ContactDto addContact(Long id, ContactDto body) {
        Contact contact = contactMapping.dtoToEntity(body);
        contact.setPersonId(id);
        return contactMapping.entityToDto(contactRepository.save(contact));
    }
}
