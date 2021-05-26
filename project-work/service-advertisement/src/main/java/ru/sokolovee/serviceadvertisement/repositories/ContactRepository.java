package ru.sokolovee.serviceadvertisement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sokolovee.serviceadvertisement.entities.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
