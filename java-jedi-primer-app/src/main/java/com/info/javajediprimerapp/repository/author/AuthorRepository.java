package com.info.javajediprimerapp.repository.author;

import com.info.javajediprimerapp.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {

    Optional<Author> findByNameIgnoreCaseAndSurnameIgnoreCase(String name,String surname);

}
