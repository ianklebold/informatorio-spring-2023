package com.info.javajediprimerapp.model.dto.book;

import com.info.javajediprimerapp.domain.Category;
import com.info.javajediprimerapp.model.dto.author.AuthorDTO;
import com.info.javajediprimerapp.model.dto.category.CategoryResponseDTO;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookResponseDTO {
    private String title;
    private String isbn;
    private int numberPages;
    private AuthorDTO authorDTO;
    private List<CategoryResponseDTO> categoryDTOS;
}
