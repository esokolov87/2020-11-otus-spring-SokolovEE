package ru.sokolovee.spring08.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sokolovee.spring08.documents.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

}
