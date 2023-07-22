package com.info.javajediprimerapp.mapper.location.impl;

import com.info.javajediprimerapp.domain.Location;
import com.info.javajediprimerapp.mapper.location.LocationMapper;
import com.info.javajediprimerapp.model.dto.location.LocationDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LocationMapperImpl implements LocationMapper {
    @Override
    public LocationDTO locationToLocationDTO(Location location) {
        return LocationDTO
                .builder()
                .address(location.getAddress())
                .city(location.getCity())
                .country(location.getCountry())
                .build();
    }

    @Override
    public Location locationDTOToLocation(LocationDTO locationDTO) {
        return Location.builder()
                .uuid(UUID.randomUUID())
                .address(locationDTO.getAddress())
                .country(locationDTO.getCountry())
                .city(locationDTO.getCity())
                .build();
    }
}
