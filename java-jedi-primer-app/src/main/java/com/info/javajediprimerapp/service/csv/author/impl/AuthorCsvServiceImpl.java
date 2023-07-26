package com.info.javajediprimerapp.service.csv.author.impl;

import com.info.javajediprimerapp.model.csv.author.AuthorCsvRecord;
import com.info.javajediprimerapp.service.csv.author.AuthorCsvService;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
public class AuthorCsvServiceImpl implements AuthorCsvService {
    @Override
    public List<AuthorCsvRecord> convertCSV(File file) throws FileNotFoundException {
        return new CsvToBeanBuilder<AuthorCsvRecord>(new FileReader(file))
                        .withType(AuthorCsvRecord.class)
                        .build()
                        .parse();
    }
}
