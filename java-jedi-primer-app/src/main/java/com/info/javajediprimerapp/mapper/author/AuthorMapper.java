package com.info.javajediprimerapp.mapper.author;

import com.info.javajediprimerapp.domain.Author;
import com.info.javajediprimerapp.model.dto.AuthorDTO;

public interface AuthorMapper {
    Author authorDtoToAuthor(AuthorDTO author);

    AuthorDTO authorToAuthorDto(Author author);
}
