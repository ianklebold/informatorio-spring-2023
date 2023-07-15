package com.info.javajediprimerapp.bootstrap;

import com.info.javajediprimerapp.domain.Book;
import com.info.javajediprimerapp.domain.Category;
import com.info.javajediprimerapp.model.csv.BookCsvRecord;
import com.info.javajediprimerapp.repository.book.BookRepository;
import com.info.javajediprimerapp.repository.category.CategoryRepository;
import com.info.javajediprimerapp.service.csv.book.BookCsvService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class BootsrapData implements CommandLineRunner {

    private final BookRepository bookRepository;

    private final BookCsvService bookCsvService;

    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("Corriendo BootsrapData");

        loadCategoryData();
        //loadBookData();
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

    /*
    private void loadBookData() throws FileNotFoundException {
        if (bookRepository.count() < 100){
            File file = ResourceUtils.getFile("classpath:csvdata/book_data.csv");
            List<BookCsvRecord> bookCsvRecordList = bookCsvService.convertCSV(file);

            if (!bookCsvRecordList.isEmpty()){
                log.info("Cargando base de datos con libros");
                for (BookCsvRecord bookCsvRecord: bookCsvRecordList) {
                    bookRepository.save(
                            Book.builder()
                                    .uuid(UUID.randomUUID())
                                    .isbn(bookCsvRecord.getIsbn())
                                    .title(bookCsvRecord.getTitle())
                                    .author(bookCsvRecord.getAuthor())
                                    .numberPages(Integer.parseInt(bookCsvRecord.getNumberPage()))
                                    .build()
                    );
                }
            }
        }
    } */

}
