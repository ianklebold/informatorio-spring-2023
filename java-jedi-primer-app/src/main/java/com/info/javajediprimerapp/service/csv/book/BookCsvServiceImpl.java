package com.info.javajediprimerapp.service.csv.book;

import com.info.javajediprimerapp.domain.Book;
import com.info.javajediprimerapp.model.csv.BookCSVRecord;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
public class BookCsvServiceImpl implements BookCsvService{
    @Override
    public List<BookCSVRecord> convertCSV(File csvFile) throws FileNotFoundException {
        List<BookCSVRecord> bookCSVRecords = new CsvToBeanBuilder<BookCSVRecord>(new FileReader(csvFile))
                .withType(BookCSVRecord.class)
                .build()
                .parse();

        return bookCSVRecords;
    }
}
