package com.info.javajediprimerapp.mapper.author.impl;

import com.info.javajediprimerapp.domain.Author;
import com.info.javajediprimerapp.mapper.author.AuthorMapper;
import com.info.javajediprimerapp.model.dto.author.AuthorDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class AuthorMapperImpl implements AuthorMapper {
    @Override
    public Author authorDtoToAuthor(AuthorDTO author) {
        return Author.builder()
                .uuid(UUID.randomUUID())
                .name(author.getName())
                .surname(author.getSurname())
                .dateOfBirth(getLocalDateTime(author.getDateOfBirth()))
                .build();
    }

    @Override
    public AuthorDTO authorToAuthorDto(Author author) {
        return AuthorDTO.builder()
                .surname(author.getSurname())
                .name(author.getName())
                .dateOfBirth(getLocalDateTime(author.getDateOfBirth()))
                .build();
    }

    private LocalDateTime getLocalDateTime(String date){
        if (!date.isBlank()){
            String[] parts = date.split("/");
            return LocalDateTime.of(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]),Integer.parseInt(parts[2]),0,0);
        }
        return null;
    }

    private String getLocalDateTime(LocalDateTime localDateTime){
        StringBuffer stringBuffer = new StringBuffer();
        return stringBuffer.append(localDateTime.getYear())
                .append("/")
                .append(localDateTime.getMonthValue())
                .append("/")
                .append(localDateTime.getDayOfYear())
                .toString();
    }

}
