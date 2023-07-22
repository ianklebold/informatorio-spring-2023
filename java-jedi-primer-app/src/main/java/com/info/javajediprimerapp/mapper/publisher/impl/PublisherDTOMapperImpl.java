package com.info.javajediprimerapp.mapper.publisher.impl;

import com.info.javajediprimerapp.domain.Publisher;
import com.info.javajediprimerapp.mapper.publisher.PublisherResponseDTOMapper;
import com.info.javajediprimerapp.model.dto.publisher.PublisherResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class PublisherDTOMapperImpl implements PublisherResponseDTOMapper {

    @Override
    public PublisherResponseDTO publisherToPublisherResponseDTO(Publisher publisher) {
        return PublisherResponseDTO.builder()
                .name(publisher.getName())
                .cellphone(publisher.getCellphone())
                .webSite(publisher.getWebSite())
                .build();

    }
}
