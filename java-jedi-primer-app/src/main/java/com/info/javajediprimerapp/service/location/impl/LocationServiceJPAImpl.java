package com.info.javajediprimerapp.service.location.impl;

import com.info.javajediprimerapp.domain.Location;
import com.info.javajediprimerapp.model.dto.location.LocationDTO;
import com.info.javajediprimerapp.repository.location.LocationRepository;
import com.info.javajediprimerapp.service.location.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class LocationServiceJPAImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Override
    public Location createLocation(LocationDTO locationDTO) {
            Location location = Location.builder()
                .uuid(UUID.randomUUID())
                .country(locationDTO.getCountry())
                .address(locationDTO.getAddress())
                .city(locationDTO.getCity())
                .build();

            return locationRepository.save(location);
    }
}
