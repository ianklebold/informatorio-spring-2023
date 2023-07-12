package com.info.javajediprimerapp.service.author.impl;

import com.info.javajediprimerapp.domain.Author;
import com.info.javajediprimerapp.mapper.author.AuthorMapper;
import com.info.javajediprimerapp.model.dto.author.AuthorDTO;
import com.info.javajediprimerapp.repository.author.AuthorRepository;
import com.info.javajediprimerapp.service.author.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorServiceJPAImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;

    @Override
    public Author createAuthor(AuthorDTO authorDTO) {
        Author author = authorMapper.authorDtoToAuthor(authorDTO);
        return authorRepository.save(author);
    }

    @Override
    public boolean deleteAuthor(UUID uuid) {
        if (authorRepository.existsById(uuid)){
            authorRepository.deleteById(uuid);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Author> updateAuthor(UUID uuid, AuthorDTO authorDTO) {
        Optional<Author> author = authorRepository.findById(uuid);

        if (author.isPresent()){
            Author authorUpdated = authorMapper.authorDtoToAuthor(authorDTO);
            updatingAuthor(author.get(),authorUpdated);
            authorRepository.save(author.get());
            return author;
        }

        return Optional.empty();
    }

    @Override
    public Optional<AuthorDTO> getAuthorById(UUID uuid) {

        Optional<Author> author = authorRepository.findById(uuid);

        if (author.isPresent()){
            return Optional.of(authorMapper.authorToAuthorDto(author.get()));
        }

        return Optional.empty();
    }

    @Override
    public Optional<AuthorDTO> getAuthorByNameAndSurname(String name, String surname) {

        Optional<Author> author = authorRepository.findByNameIgnoreCaseAndSurnameIgnoreCase(name,surname);

        if (author.isPresent()){
            return Optional.of(authorMapper.authorToAuthorDto(author.get()));
        }

        return Optional.empty();
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        List<AuthorDTO> listAuthors = new ArrayList<>();
        for (Author author: authorRepository.findAll()) {
            listAuthors.add(authorMapper.authorToAuthorDto(author));
        }
        return listAuthors;
    }

    private void updatingAuthor(Author author,Author authorUpdated){
        if (!authorUpdated.getName().isBlank()){
            author.setName(authorUpdated.getName());
        }

        if (!authorUpdated.getSurname().isBlank()){
            author.setSurname(authorUpdated.getSurname());
        }

        if (authorUpdated.getDateOfBirth() != null){
            author.setDateOfBirth(authorUpdated.getDateOfBirth());
        }
    }
}
