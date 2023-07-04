package com.info.javajediprimerapp.service.csv.book;

import com.info.javajediprimerapp.domain.Book;
import com.info.javajediprimerapp.model.csv.BookCSVRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface BookCsvService {
    List<BookCSVRecord> convertCSV(File csvFile) throws FileNotFoundException;
}
