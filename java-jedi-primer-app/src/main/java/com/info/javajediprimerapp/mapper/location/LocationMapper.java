package com.info.javajediprimerapp.mapper.location;

import com.info.javajediprimerapp.domain.Location;
import com.info.javajediprimerapp.model.dto.location.LocationDTO;

public interface LocationMapper {
    LocationDTO locationToLocationDTO(Location location);
}
