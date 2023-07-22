package com.info.javajediprimerapp.model.dto.location;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LocationDTO {
    private String address;
    private String city;
    private String country;
}
