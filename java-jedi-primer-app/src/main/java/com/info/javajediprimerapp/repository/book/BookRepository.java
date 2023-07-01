package com.info.javajediprimerapp.repository.book;

import com.info.javajediprimerapp.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    //Query Methods
    Optional<Book> findBookByTitleEqualsIgnoreCase(String title);
}
