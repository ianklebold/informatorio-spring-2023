package com.info.javajediprimerapp.controller;

import com.info.javajediprimerapp.domain.Book;
import com.info.javajediprimerapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController //Anotacion a nivel de clase
public class BookController {

    @Autowired
    BookService bookService;

    //Get --> Obtener
    // http://localhost:8080/api/v1/book
    @GetMapping("/api/v1/book")  //Anotacion a nivel de metodo
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    //Post --> Crear
    @PostMapping("/api/v1/book")
    public Book createBook(@RequestBody Book book){
        return bookService.createBook(book);
    }

    @GetMapping("/api/v1/book/{name}")
    public Book getBookByName(@PathVariable(value = "name") String name){
        Book book = bookService.getBookById(name);
        if (book == null){
            System.out.println("Libro no encontrado");
            return null;
        }else {
            return book;
        }
    }




}
