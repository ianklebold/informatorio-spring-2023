package com.info.javajediprimerapp.mapper.publisher.impl;

import com.info.javajediprimerapp.domain.Publisher;
import com.info.javajediprimerapp.mapper.location.LocationMapper;
import com.info.javajediprimerapp.mapper.publisher.PublisherMapper;
import com.info.javajediprimerapp.model.dto.publisher.PublisherDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class PublisherMapperImpl implements PublisherMapper {

    private final LocationMapper locationMapper;

    @Override
    public Publisher publisherDTOToPublisher(PublisherDTO publisher) {
        return Publisher
                .builder()
                .uuid(UUID.randomUUID())
                .name(publisher.getName())
                .cellphone(publisher.getCellphone())
                .webSite(publisher.getWebSite())
                .build();
    }

    @Override
    public PublisherDTO publisherToPublisherDTO(Publisher publisher) {
        return PublisherDTO
                .builder()
                .name(publisher.getName())
                .webSite(publisher.getWebSite())
                .cellphone(publisher.getCellphone())
                .locationDTO(locationMapper.locationToLocationDTO(publisher.getLocation()))
                .build();
    }


}
