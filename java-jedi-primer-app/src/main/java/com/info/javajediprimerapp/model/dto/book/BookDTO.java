package com.info.javajediprimerapp.model.dto.book;

import com.info.javajediprimerapp.model.dto.publisher.PublisherDTO;
import com.info.javajediprimerapp.model.dto.review.ReviewDTO;
import lombok.*;

import java.util.ArrayList;
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
    private List<String> listCategoriesIds = new ArrayList<>();
    private PublisherDTO publisherDTO;
    private List<ReviewDTO> reviews = new ArrayList<>();
}
