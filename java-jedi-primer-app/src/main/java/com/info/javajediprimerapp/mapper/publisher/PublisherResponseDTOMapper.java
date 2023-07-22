package com.info.javajediprimerapp.mapper.publisher;

import com.info.javajediprimerapp.domain.Publisher;
import com.info.javajediprimerapp.model.dto.publisher.PublisherResponseDTO;

public interface PublisherResponseDTOMapper {
    PublisherResponseDTO publisherToPublisherResponseDTO(Publisher publisher);
}
