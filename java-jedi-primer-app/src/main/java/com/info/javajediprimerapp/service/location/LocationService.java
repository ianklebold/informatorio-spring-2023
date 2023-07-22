package com.info.javajediprimerapp.service.location;

import com.info.javajediprimerapp.domain.Location;
import com.info.javajediprimerapp.model.dto.location.LocationDTO;

public interface LocationService {
    Location createLocation(LocationDTO locationDTO);
}
