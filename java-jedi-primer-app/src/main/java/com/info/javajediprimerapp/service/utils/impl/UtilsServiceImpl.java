package com.info.javajediprimerapp.service.utils.impl;

import com.info.javajediprimerapp.service.utils.UtilsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UtilsServiceImpl implements UtilsService {
    @Override
    public LocalDateTime getLocalDateTimeFromString(String date) {
        String[] dateSplit = date.split("/");

        return LocalDateTime.of(Integer.parseInt(dateSplit[2]),
                Integer.parseInt(dateSplit[1]),
                Integer.parseInt(dateSplit[0]),0,0,0);
    }
}
