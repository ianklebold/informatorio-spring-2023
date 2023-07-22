package com.info.javajediprimerapp.service.location.impl;

import com.info.javajediprimerapp.domain.Location;
import com.info.javajediprimerapp.mapper.location.LocationMapper;
import com.info.javajediprimerapp.model.dto.location.LocationDTO;
import com.info.javajediprimerapp.repository.location.LocationRepository;
import com.info.javajediprimerapp.service.location.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LocationServiceJPAImpl implements LocationService {

    private final LocationRepository locationRepository;

    private final LocationMapper locationMapper;

    @Override
    public Location createLocation(LocationDTO locationDTO) {
        Location location = locationMapper.locationDTOToLocation(locationDTO);

        return locationRepository.save(location);
    }
}
