package com.info.javajediprimerapp.mapper.publisher.impl;

import com.info.javajediprimerapp.domain.Publisher;
import com.info.javajediprimerapp.mapper.publisher.PublisherMapper;
import com.info.javajediprimerapp.model.dto.publisher.PublisherDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PublisherMapperImpl implements PublisherMapper {
    @Override
    public Publisher publisherToPublisherDTO(PublisherDTO publisher) {
        return Publisher
                .builder()
                .uuid(UUID.randomUUID())
                .name(publisher.getName())
                .cellphone(publisher.getCellphone())
                .webSite(publisher.getWebSite())
                .build();
    }
}
