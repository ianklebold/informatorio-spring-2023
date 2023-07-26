package com.info.javajediprimerapp.service.csv.book;

import com.info.javajediprimerapp.model.csv.book.BookCsvRecord;
import com.info.javajediprimerapp.model.csv.book.BookCsvV2Record;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface BookCsvService {
    List<BookCsvV2Record> convertCSV(File file) throws FileNotFoundException;
}
