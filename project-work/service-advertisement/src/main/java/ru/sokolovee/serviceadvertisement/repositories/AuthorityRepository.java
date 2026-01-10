package ru.sokolovee.serviceadvertisement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sokolovee.serviceadvertisement.entities.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
