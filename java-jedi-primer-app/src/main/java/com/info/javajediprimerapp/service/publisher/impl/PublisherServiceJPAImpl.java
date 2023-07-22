package com.info.javajediprimerapp.service.publisher.impl;

import com.info.javajediprimerapp.domain.Publisher;
import com.info.javajediprimerapp.mapper.publisher.PublisherMapper;
import com.info.javajediprimerapp.model.dto.publisher.PublisherDTO;
import com.info.javajediprimerapp.repository.publisher.PublisherRepository;
import com.info.javajediprimerapp.service.location.LocationService;
import com.info.javajediprimerapp.service.publisher.PublisherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class PublisherServiceJPAImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    private final LocationService locationService;

    private final PublisherMapper publisherMapper;

    @Override
    public Publisher createPublisher(PublisherDTO publisherDTO) {

        Publisher publisher = publisherMapper.publisherToPublisherDTO(publisherDTO);
        publisher.setLocation(locationService.createLocation(publisherDTO.getLocationDTO()));

        return publisherRepository.save(publisher);
    }


}
