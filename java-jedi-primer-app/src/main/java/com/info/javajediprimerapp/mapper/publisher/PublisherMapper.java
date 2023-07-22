package com.info.javajediprimerapp.mapper.publisher;

import com.info.javajediprimerapp.domain.Publisher;
import com.info.javajediprimerapp.model.dto.publisher.PublisherDTO;

public interface PublisherMapper {
    Publisher publisherDTOToPublisher(PublisherDTO publisher);

    PublisherDTO publisherToPublisherDTO(Publisher publisher);
}
