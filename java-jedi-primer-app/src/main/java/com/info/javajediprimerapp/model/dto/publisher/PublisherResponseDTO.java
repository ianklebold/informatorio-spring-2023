package com.info.javajediprimerapp.model.dto.publisher;

import com.info.javajediprimerapp.model.dto.review.ReviewDTO;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PublisherResponseDTO {
    private String name;
    private String cellphone;
    private String webSite;
}
