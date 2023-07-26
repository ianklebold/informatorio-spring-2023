package com.info.javajediprimerapp.model.csv.author;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorCsvRecord {

    @CsvBindByName(column = "name")
    private String name;

    @CsvBindByName(column = "surname")
    private String surname;

    @CsvBindByName(column = "dateOfBirth")
    private String dateOfBirth;
}
