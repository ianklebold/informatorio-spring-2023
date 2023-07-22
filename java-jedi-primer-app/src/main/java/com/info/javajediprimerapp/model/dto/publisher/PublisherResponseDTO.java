package com.info.javajediprimerapp.model.dto.publisher;

import lombok.*;


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
