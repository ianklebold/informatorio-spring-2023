package com.info.javajediprimerapp.bootstrap;

import com.info.javajediprimerapp.domain.Book;
import com.info.javajediprimerapp.model.csv.BookCSVRecord;
import com.info.javajediprimerapp.repository.book.BookRepository;
import com.info.javajediprimerapp.service.csv.book.BookCsvService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final BookRepository bookRepository;

    private final BookCsvService bookCsvService;

    @Override
    public void run(String... args) throws Exception {
        loadBookData();
    }

    @Transactional //Si algo de lo de aqui no se carga correctamente hace un Rollback
    private void loadBookData() throws FileNotFoundException {
        if (bookRepository.count() < 100){
            File file = ResourceUtils.getFile("classpath:csvdata/book_data.csv");
            List<BookCSVRecord> recordList = bookCsvService.convertCSV(file);

            if (!recordList.isEmpty()){
                for (BookCSVRecord bookCsvRecord: recordList) {
                    bookRepository.save(
                            Book.builder()
                                    .uuid(UUID.randomUUID())
                                    .isbn(bookCsvRecord.getIsbn())
                                    .author(bookCsvRecord.getAuthor())
                                    .title(bookCsvRecord.getTitle())
                                    .number(Integer.parseInt(bookCsvRecord.getNumberPage()))
                                    .build()
                    );
                }
            }

        }
    }
}
