package com.info.javajediprimerapp.model.csv;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookCsvRecord {
    @CsvBindByName(column = "isbn")
    private String isbn;
    @CsvBindByName(column = "title")
    private String title;
    @CsvBindByName(column = "author")
    private String author;
    @CsvBindByName(column = "number_page")
    private String numberPage;
}
