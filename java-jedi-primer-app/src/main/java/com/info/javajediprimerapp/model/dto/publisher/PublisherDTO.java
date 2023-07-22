package com.info.javajediprimerapp.model.dto.publisher;

import com.info.javajediprimerapp.model.dto.location.LocationDTO;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PublisherDTO {
    private String name;
    private String cellphone;
    private String webSite;
    private LocationDTO locationDTO;
}
