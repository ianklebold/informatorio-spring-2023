package com.info.javajediprimerapp.service.csv.book;

import com.info.javajediprimerapp.model.csv.BookCsvRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface BookCsvService {
    List<BookCsvRecord> convertCSV(File file) throws FileNotFoundException;
}
