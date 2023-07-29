package com.info.javajediprimerapp.repository.book;

import com.info.javajediprimerapp.domain.Book;
import com.info.javajediprimerapp.repository.book.projection.ProjectionOnlyTitleBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    
    @Query(value = "SELECT b2.uuid, isbn, number_pages, b2.title, author_uuid, publisher_uuid " +
            "FROM (" +
            "         SELECT b.uuid as idbook" +
            "         FROM book b JOIN author a on a.uuid = b.author_uuid" +
            "         WHERE a.date_of_birth >= '1980-01-01' and a.date_of_birth < '1990-01-01'" +
            "     ) as result1 JOIN book_reviews br ON br.book_uuid = result1.idbook JOIN review r ON r.uuid = br.reviews_uuid JOIN book b2 on b2.uuid = br.book_uuid" +
            " WHERE r.calification IN ('THREE_STARS','FOUR_STARS','FIVE_STARS')",nativeQuery = true)
    List<Book> findBooksWithAuthorBornInTheYear80sWithAlmostOneReviewWhit3Stars();
    
    @Query(value = "SELECT * FROM book b WHERE b.title IN :titles", nativeQuery = true)
    List<Book> findBooksExistInList(@Param("titles") Collection<String> titles);

    @Query(value ="SELECT b.uuid, isbn, number_pages, title, author_uuid, publisher_uuid " +
            "FROM book b JOIN author a on a.uuid = b.author_uuid " +
            "WHERE a.date_of_birth >= '1980-01-01' and a.date_of_birth < '1990-01-01'" ,nativeQuery = true)
    List<Book> findBooksWithAuthorBornInTheYear80s();

    // Projection con query SQL
    @Query(value = "SELECT b.title FROM book AS b WHERE b.title like :title",nativeQuery = true)
    List<ProjectionOnlyTitleBook> findBooksByTitleStartingWithQueryNativeNamedProjection(@Param("title") String title);

    // Projection con query method
    List<ProjectionOnlyTitleBook> findBooksByTitleStartingWith(String title);

    //@Query SQL Parametro nombrado
    @Query(value = "SELECT * FROM book AS b WHERE b.title like :title",nativeQuery = true)
    List<Book> findBooksByTitleStartingWithQueryNativeNamed(@Param("title") String title);


    //@Query HQL parametros nombrados
    @Query("select b from Book AS b where b.title like :title%")
    List<Book> findBooksByTitleStartingWithQueryNamed(@Param("title") String title);

    //@Query HQL parametros no nombrados
    @Query("select b from Book AS b where b.title like ?1%")
    List<Book> findBooksByTitleStartingWithQuery(String title);

    //Query Methods
    Optional<Book> findBookByTitleEqualsIgnoreCase(String title);
}
