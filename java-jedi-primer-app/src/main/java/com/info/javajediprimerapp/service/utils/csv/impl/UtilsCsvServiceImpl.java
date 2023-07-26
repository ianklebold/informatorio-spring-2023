package com.info.javajediprimerapp.service.utils.csv.impl;

import com.info.javajediprimerapp.service.utils.csv.UtilsCsvService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UtilsCsvServiceImpl implements UtilsCsvService {
    @Override
    public LocalDateTime getLocalDateTimeFromString(String date) {
        String[] dateSplit = date.split("/");
        return LocalDateTime.of(Integer.parseInt(dateSplit[2]),Integer.parseInt(dateSplit[1]),Integer.parseInt(dateSplit[0]),0,0,0);
    }
}
