package com.info.javajediprimerapp.bootstrap;

import com.info.javajediprimerapp.domain.*;
import com.info.javajediprimerapp.enumeration.CalificationEnum;
import com.info.javajediprimerapp.model.csv.author.AuthorCsvRecord;
import com.info.javajediprimerapp.model.csv.book.BookCsvRecord;
import com.info.javajediprimerapp.model.csv.book.BookCsvV2Record;
import com.info.javajediprimerapp.repository.author.AuthorRepository;
import com.info.javajediprimerapp.repository.book.BookRepository;
import com.info.javajediprimerapp.repository.category.CategoryRepository;
import com.info.javajediprimerapp.repository.publisher.PublisherRepository;
import com.info.javajediprimerapp.repository.reviews.ReviewRepository;
import com.info.javajediprimerapp.service.csv.author.AuthorCsvService;
import com.info.javajediprimerapp.service.csv.book.BookCsvService;
import com.info.javajediprimerapp.service.utils.csv.UtilsCsvService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class BootsrapData implements CommandLineRunner {



    private final BookCsvService bookCsvService;

    private final AuthorCsvService authorCsvRecord;

    private final UtilsCsvService utilsCsvService;
    private final CategoryRepository categoryRepository;

    private final BookRepository bookRepository;

    private final ReviewRepository reviewRepository;

    private final PublisherRepository publisherRepository;

    private final AuthorRepository authorRepository;


    @Override
    public void run(String... args) throws Exception {
        log.info("Corriendo BootsrapData");

        loadCategoryData();
        loadAuthorData();
        loadBookData();
    }

    private void loadCategoryData(){
        if (categoryRepository.count() == 0){
            Category category1 = Category.builder()
                    .uuid(UUID.randomUUID())
                    .name("Ficcion")
                    .build();

            Category category2 = Category.builder()
                    .uuid(UUID.randomUUID())
                    .name("Misterio")
                    .build();

            Category category3 = Category.builder()
                    .uuid(UUID.randomUUID())
                    .name("Romance")
                    .build();

            categoryRepository.saveAll(List.of(category1,category2,category3));
        }
    }

    private void loadAuthorData() throws FileNotFoundException {
        if (authorRepository.count() < 1000){
            File file = ResourceUtils.getFile("classpath:csvdata/author_data.csv");
            List<AuthorCsvRecord> authorCsvRecordList = authorCsvRecord.convertCSV(file);

            if (!authorCsvRecordList.isEmpty()){
                log.info("Cargando base de datos con autores");
                for (AuthorCsvRecord author: authorCsvRecordList) {
                   authorRepository.save(
                           Author.builder()
                                   .uuid(UUID.randomUUID())
                                   .name(author.getName())
                                   .surname(author.getSurname())
                                   .dateOfBirth(utilsCsvService.getLocalDateTimeFromString(author.getDateOfBirth()))
                                   .build()
                   );
                }
            }
        }
    }


    private void loadBookData() throws FileNotFoundException {

        List<Author> authorList = authorRepository.findAll();

        if (bookRepository.count() < 1000 && authorList.size() >= 1000){
            File file = ResourceUtils.getFile("classpath:csvdata/book_data_v2.csv");
            List<BookCsvV2Record> bookCsvRecordList = bookCsvService.convertCSV(file);

            if (!bookCsvRecordList.isEmpty()){
                log.info("Cargando base de datos con libros");

                for (int i = 0; i < 1000; i++) {
                    bookRepository.save(
                            getNewBookCreated(authorList.get(i), bookCsvRecordList.get(i))
                    );
                }
            }
        }
    }

    private Book getNewBookCreated(Author author,BookCsvV2Record bookCsvV2Record){
            return Book.builder()
                    .uuid(UUID.randomUUID())
                    .isbn(bookCsvV2Record.getIsbn())
                    .title(bookCsvV2Record.getTitle())
                    .numberPages(Integer.parseInt(bookCsvV2Record.getNumberPages()))
                    .reviews(
                            reviewRepository.saveAll(
                                    List.of(
                                            Review.builder()
                                                    .uuid(UUID.randomUUID())
                                                    .title(bookCsvV2Record.getTitleReview())
                                                    .content(bookCsvV2Record.getContentReview())
                                                    .calification(CalificationEnum.valueOf(bookCsvV2Record.getCalification()))
                                                    .dateOfCreation(LocalDateTime.now()).build()
                                    )
                            )

                    )
                    .publisher(
                            publisherRepository.save(
                                    Publisher
                                            .builder()
                                            .uuid(UUID.randomUUID())
                                            .name(bookCsvV2Record.getNamePublisher())
                                            .location(Location
                                                    .builder()
                                                    .uuid(UUID.randomUUID())
                                                    .address(bookCsvV2Record.getLocationAddress())
                                                    .city(bookCsvV2Record.getCityAddress())
                                                    .country(bookCsvV2Record.getCountryAddress())
                                                    .build()
                                            )
                                            .webSite(bookCsvV2Record.getWebsitePublisher())
                                            .cellphone(bookCsvV2Record.getCellphonePublisher())
                                            .build()
                            )
                    )
                    .author(author)
                    .categories(List.of(categoryRepository.findByNameIgnoreCase(bookCsvV2Record.getNameCategory()).get()))
                    .build();
    }

}
