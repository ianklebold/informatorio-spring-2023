package com.info.javajediprimerapp.controller;

import com.info.javajediprimerapp.domain.Book;
import com.info.javajediprimerapp.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController //Anotacion a nivel de clase
@Slf4j
public class BookController {

    //IoC Inversion de control
    BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //GET --> Obtener un recurso
    @GetMapping("/api/v1/book")
    public List<Book> getAllBooks(){

        log.info("Obteniendo todos los libros");
        return bookService.getAllBooks();
    }

    //POST --> Crear un recurso
    @PostMapping("/api/v1/book")
    public Book createBook(@RequestBody Book book){

        log.info("Creando libro");
        return bookService.createBook(book);
    }

    //PUT --> Actualizar un recurso
    @PutMapping("/api/v1/book/{uuidBook}")
    public String updateBook(@PathVariable(value = "uuidBook") UUID uuidBook, @RequestBody Book book){
        Optional<Book> bookUpdated = bookService.updateBook(uuidBook,book);

        log.info("Actualizando libro");
        return (bookUpdated.isPresent())?"/api/v1/book/"+uuidBook+" Updated":"Book not found";
    }



}
