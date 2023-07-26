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
import com.info.javajediprimerapp.service.utils.UtilsService;
import jakarta.transaction.Transactional;
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
    private final ReviewRepository reviewRepository;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    private final BookCsvService bookCsvService;

    private final CategoryRepository categoryRepository;

    private final AuthorCsvService authorCsvService;

    private final UtilsService utilsService;

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
            List<AuthorCsvRecord> authorCsvRecordList = authorCsvService.convertCSV(file);

            for (AuthorCsvRecord author: authorCsvRecordList) {
                authorRepository.save(
                        Author.builder()
                                .uuid(UUID.randomUUID())
                                .name(author.getName())
                                .surname(author.getSurname())
                                .dateOfBirth(utilsService.getLocalDateTimeFromString(author.getDateOfBirth()))
                                .build()
                );
            }

        }
    }


    private void loadBookData() throws FileNotFoundException {

        List<Author> authorList = authorRepository.findAll();

        if (bookRepository.count() < 1000 && authorList.size() >= 1000){
            File file = ResourceUtils.getFile("classpath:csvdata/book_data_v2.csv");
            List<BookCsvV2Record> bookCsvRecordList = bookCsvService.convertCSV(file);

            if (!bookCsvRecordList.isEmpty()){
                for (int i = 0; i <= 1000; i++) {
                    bookRepository.save(getBookCreated(authorList.get(i),bookCsvRecordList.get(i)));
                }
            }
        }
    }

    @Transactional
    private Book getBookCreated(Author author, BookCsvV2Record bookCsvRecord){
        return Book.builder()
                .uuid(UUID.randomUUID())
                .isbn(bookCsvRecord.getIsbn())
                .isbn(bookCsvRecord.getTitle())
                .title(bookCsvRecord.getTitle())
                .numberPages(Integer.parseInt(bookCsvRecord.getNumberPages()))
                .reviews(reviewRepository.saveAll(
                        List.of(
                                Review.builder()
                                        .uuid(UUID.randomUUID())
                                        .title(bookCsvRecord.getTitleReview())
                                        .content(bookCsvRecord.getContentReview())
                                        .calification(CalificationEnum.valueOf(bookCsvRecord.getCalification()))
                                        .dateOfCreation(LocalDateTime.now()).build()
                        ))
                )
                .publisher(
                        publisherRepository.save(Publisher.builder()
                                .uuid(UUID.randomUUID())
                                .name(bookCsvRecord.getNamePublisher())
                                .location(Location.builder()
                                        .uuid(UUID.randomUUID())
                                        .address(bookCsvRecord.getLocationAddress())
                                        .city(bookCsvRecord.getCityAddress())
                                        .country(bookCsvRecord.getCountryAddress())
                                        .build()
                                )
                                .webSite(bookCsvRecord.getWebsitePublisher())
                                .cellphone(bookCsvRecord.getCellphonePublisher())
                                .build()
                        )
                )
                .author(author)
                .categories(List.of(categoryRepository.findByNameIgnoreCase(bookCsvRecord.getNameCategory()).get()))
                .build();

    }

}
