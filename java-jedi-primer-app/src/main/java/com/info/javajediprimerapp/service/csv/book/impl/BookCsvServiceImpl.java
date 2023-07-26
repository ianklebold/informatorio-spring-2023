package com.info.javajediprimerapp.service.csv.book.impl;

import com.info.javajediprimerapp.model.csv.book.BookCsvRecord;
import com.info.javajediprimerapp.model.csv.book.BookCsvV2Record;
import com.info.javajediprimerapp.service.csv.book.BookCsvService;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Slf4j
@Service
public class BookCsvServiceImpl implements BookCsvService {
    @Override
    public List<BookCsvV2Record> convertCSV(File file) throws FileNotFoundException {
        List<BookCsvV2Record> bookCsvRecordList =
                new CsvToBeanBuilder<BookCsvV2Record>(new FileReader(file))
                        .withType(BookCsvV2Record.class)
                        .build()
                        .parse();
        log.info("Conviertiendo CSV a lista de Libros");
        return bookCsvRecordList;
    }
}
