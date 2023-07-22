package com.info.javajediprimerapp.mapper.publisher;

import com.info.javajediprimerapp.domain.Publisher;
import com.info.javajediprimerapp.model.dto.publisher.PublisherDTO;

public interface PublisherMapper {
    Publisher publisherToPublisherDTO(PublisherDTO publisher);
}
