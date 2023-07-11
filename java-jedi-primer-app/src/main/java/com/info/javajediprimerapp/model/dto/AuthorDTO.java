package com.info.javajediprimerapp.model.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorDTO {
    private String name;
    private String surname;
    private String dateOfBirth;
}
