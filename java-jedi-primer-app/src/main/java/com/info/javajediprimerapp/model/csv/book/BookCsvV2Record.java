package com.info.javajediprimerapp.model.csv.book;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookCsvV2Record {
    @CsvBindByName(column = "title")
    private String title;
    @CsvBindByName(column = "isbn")
    private String isbn;
    @CsvBindByName(column = "titleReview")
    private String titleReview;
    @CsvBindByName(column = "contentReview")
    private String contentReview;
    @CsvBindByName(column = "calification")
    private String calification;
    @CsvBindByName(column = "namePublisher")
    private String namePublisher;
    @CsvBindByName(column = "cellphonePublisher")
    private String cellphonePublisher;
    @CsvBindByName(column = "websitePublisher")
    private String websitePublisher;
    @CsvBindByName(column = "locationAddress")
    private String locationAddress;
    @CsvBindByName(column = "cityAddress")
    private String cityAddress;
    @CsvBindByName(column = "countryAddress")
    private String countryAddress;
    @CsvBindByName(column = "numberPages")
    private String numberPages;
    @CsvBindByName(column = "nameCategory")
    private String nameCategory;
}
