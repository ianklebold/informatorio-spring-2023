package com.info.javajediprimerapp.repository.book;

import com.info.javajediprimerapp.domain.Book;
import com.info.javajediprimerapp.repository.book.projection.TitleOnly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    @Query(value = "SELECT b2.uuid, b2.isbn, b2.number_pages, b2.title, b2.author_uuid, b2.publisher_uuid " +
            "FROM( " +
            "SELECT b.uuid as idbook " +
            "FROM book b JOIN author a ON a.uuid = b.author_uuid  WHERE a.date_of_birth >= '1980-01-01' and a.date_of_birth < '1990-01-01') " +
            "AS result1 JOIN book_reviews br ON br.book_uuid = result1.idbook JOIN review r ON r.uuid = br.reviews_uuid JOIN book b2 ON b2.uuid = br.book_uuid " +
            "WHERE r.calification IN ('TWO_STARS','THREE_STARS','FOUR_STARS','FIVE_STARS')"
            ,nativeQuery = true)
    List<Book> findBooksWithAuthorBornInTheYear80WithAlmostOneReviewWith3Stars();

    //Projection + @Query SQL Parametros nombrados y JOIN
    @Query(value = "SELECT b.uuid, isbn, number_pages, title, author_uuid, publisher_uuid " +
            "FROM book b JOIN author a ON a.uuid = b.author_uuid " +
            "WHERE a.date_of_birth >= '1980-01-01' and a.date_of_birth < '1990-01-01'",nativeQuery = true)
    List<Book> findBooksWithAuthorBornInTheYear80();

    //@Query SQL Parametros nombrados pasando collection
    @Query(value = "SELECT * FROM book b WHERE b.title IN :titles",nativeQuery = true)
    List<Book> findBooksInTitlesWithQueryNamedNative(@Param("titles") Collection<String> titles);

    //Projection + @Query SQL Parametros nombrados
    @Query(value = "SELECT DISTINCT(b.title) " +
                "FROM book b " +
                "WHERE b.title like :title and b.number_pages >= :numberPages",nativeQuery = true)
    List<TitleOnly> findDistinctByTitleStartingWithAndNumberPagesGreaterThanEqualWithQueryNamedNative(@Param("title") String title, @Param("numberPages") Integer numberPages);

    //Projection
    List<TitleOnly> findDistinctByTitleStartingWithAndNumberPagesGreaterThanEqual(String title,Integer numberPages);

    //@Query SQL Parametros nombrados
    @Query(value = "SELECT * FROM book WHERE title like :title",nativeQuery = true)
    List<Book> findBooksByTitleStartingWithTitleWithQueryNamedNative(@Param("title") String title);

    //@Query HQL Parametros nombrados
    @Query("select b from Book b where b.title like %:title")
    List<Book> findBooksByTitleStartingWithTitleWithQueryNamed(@Param("title") String title);

    //@Query HQL Parametros no nombrados
    @Query("select b from Book b where b.title like %?1%")
    List<Book> findBooksByTitleStartingWithTitleWithQuery(String title);

    //Query Methods
    //List<Book> findBookByTitleStartingWith(String title);

    Optional<Book> findBookByTitleEqualsIgnoreCase(String title);
}
