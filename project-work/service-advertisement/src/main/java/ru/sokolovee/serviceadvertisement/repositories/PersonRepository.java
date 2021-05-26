package ru.sokolovee.serviceadvertisement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sokolovee.serviceadvertisement.entities.Person;
import ru.sokolovee.serviceadvertisement.entities.User;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByUsername(String username);
}