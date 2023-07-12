package com.info.javajediprimerapp.model.dto.book;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO {
    private String title;
    private String isbn;
    private int numberPages;
    private String idAuthor;
}
