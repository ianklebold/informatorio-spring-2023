package com.info.javajediprimerapp.model.dto.category;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryResponseDTO {
    private UUID uuid;
    private String name;
}
