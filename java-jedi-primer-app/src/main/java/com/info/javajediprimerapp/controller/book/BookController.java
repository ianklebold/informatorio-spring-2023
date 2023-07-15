package com.info.javajediprimerapp.controller.book;

import com.info.javajediprimerapp.domain.Book;
import com.info.javajediprimerapp.exceptions.NotFoundException;
import com.info.javajediprimerapp.model.dto.book.BookDTO;
import com.info.javajediprimerapp.model.dto.book.BookResponseDTO;
import com.info.javajediprimerapp.service.book.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController //Anotacion a nivel de clase
@RequestMapping("/api/v1/book") //Todos los endpoints comparten esta URI
@Slf4j
public class BookController {

    //IoC Inversion de control
    BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //GET --> Obtener un recurso
    @GetMapping()
    public List<BookResponseDTO> getAllBooks(@RequestParam(required = false,name = "nameBook") String nameBook){
        log.info("Se esta haciendo una consulta por los libros");
        return bookService.getAllBooks();
    }

    //POST --> Crear un recurso
    @PostMapping()
    public ResponseEntity createBook(@RequestBody BookDTO book) throws NotFoundException {
        log.info("Creacion de un nuevo libro");
        Book bookCreated = bookService.createBook(book);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","/api/v1/book/"+bookCreated.getUuid());

        return new ResponseEntity(headers,HttpStatus.CREATED);
    }

    //PUT --> Actualizar un recurso
    @PutMapping("/{idBook}")
    public ResponseEntity updateBook(@PathVariable(value = "idBook")UUID idBook,@RequestBody BookDTO bookUpdated)
            throws NotFoundException {
        Optional<BookDTO> book = bookService.updateBook(idBook,bookUpdated);

        if(book.isEmpty()){
            log.warn("Libro no encontrado");
            throw new NotFoundException();
        }else {
            log.info("Libro actualizado");
            return  new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    //Delete --> Eliminar un recurso
    @DeleteMapping("/{idBook}")
    public ResponseEntity deleteBook(@PathVariable(value = "idBook")UUID idBook) throws NotFoundException {
        boolean isBookDeleted = bookService.deleteBook(idBook);

        if(isBookDeleted){
            log.info("Libro no encontrado");
            throw new NotFoundException();

        }else {
            log.warn("Libro eliminado");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    //Por variable --> Informacion en URL
    //Por parametro --> Parametro de la request
    @GetMapping("/{idBook}")
    public BookDTO getBookById(@PathVariable(value = "idBook") UUID idBook) throws NotFoundException {
        return bookService.getBookById(idBook).orElseThrow(NotFoundException::new);
    }


}
