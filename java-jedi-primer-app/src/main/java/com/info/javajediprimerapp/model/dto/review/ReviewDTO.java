package com.info.javajediprimerapp.model.dto.review;

import com.info.javajediprimerapp.enumeration.CalificationEnum;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReviewDTO {
    private String title;
    private String content;
    private CalificationEnum calification;
}
