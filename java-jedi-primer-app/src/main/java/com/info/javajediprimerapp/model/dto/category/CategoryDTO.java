package com.info.javajediprimerapp.model.dto.category;

import com.info.javajediprimerapp.model.dto.book.BookDTO;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryDTO {
    private String UUID;
    private String name;
    private List<BookDTO> bookDTOList;



}
