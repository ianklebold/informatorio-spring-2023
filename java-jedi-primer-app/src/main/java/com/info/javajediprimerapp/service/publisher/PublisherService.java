package com.info.javajediprimerapp.service.publisher;

import com.info.javajediprimerapp.domain.Publisher;
import com.info.javajediprimerapp.model.dto.publisher.PublisherDTO;

public interface PublisherService {
    Publisher createPublisher(PublisherDTO publisherDTO);
}
