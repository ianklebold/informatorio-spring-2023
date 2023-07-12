package com.info.javajediprimerapp.controller.author;

import com.info.javajediprimerapp.domain.Author;
import com.info.javajediprimerapp.exceptions.NotFoundException;
import com.info.javajediprimerapp.model.dto.author.AuthorDTO;
import com.info.javajediprimerapp.service.author.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/author")
@RequiredArgsConstructor
@Slf4j
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping()
    public ResponseEntity createAuthor(@RequestBody AuthorDTO authorDTO){
        log.info("Creacion de un nuevo Autor");
        Author authorCreated = authorService.createAuthor(authorDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","/api/v1/author/"+ authorCreated.getUuid());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{idAuthor}")
    public ResponseEntity updateAuthor(@PathVariable(value = "idAuthor") UUID idAuthor,@RequestBody AuthorDTO authorUpdated)
            throws NotFoundException {
        Optional<Author> book = authorService.updateAuthor(idAuthor,authorUpdated);

        if(book.isEmpty()){
            log.warn("Autor no encontrado");
            throw new NotFoundException();
        }else {
            log.info("Autor actualizado");
            return  new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{idAuthor}")
    public ResponseEntity deleteAuthor(@PathVariable(value = "idAuthor")UUID idAuthor) throws NotFoundException{
        boolean isDeletedAuthor = authorService.deleteAuthor(idAuthor);

        if (isDeletedAuthor){
            log.warn("Autor eliminado");
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }else {
            log.info("Autor no encontrado");
            throw new NotFoundException();
        }
    }

    @GetMapping()
    public List<AuthorDTO> getAllAuthors(){
        log.info("Se esta haciendo una consulta por los autores");
        return authorService.getAllAuthors();
    }

    @GetMapping("/{idAuthor}")
    public AuthorDTO getAuthorById(@PathVariable(value = "idAuthor") UUID idAuthor) throws NotFoundException{
        return authorService.getAuthorById(idAuthor).orElseThrow(NotFoundException::new);
    }

}
