package com.info.javajediprimerapp.model.dto.book;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookDTO {
    private String title;
    private String isbn;
    private int numberPages;
    private String idAuthor;
    private List<String> categoriesIds;
}
