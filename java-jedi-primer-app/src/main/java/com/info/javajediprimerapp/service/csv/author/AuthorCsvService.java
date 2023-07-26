package com.info.javajediprimerapp.service.csv.author;

import com.info.javajediprimerapp.model.csv.author.AuthorCsvRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface AuthorCsvService {
    List<AuthorCsvRecord> convertCSV(File file) throws FileNotFoundException;
}
