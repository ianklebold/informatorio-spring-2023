package com.info.javajediprimerapp.controller;

import com.info.javajediprimerapp.domain.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    @GetMapping("/api/v1/despedida")
    public String goodByeWorld(){
        return "Adios mundo cruel!";
    }

    @GetMapping("/api/v1/saludo")
    public String goodByeWorld(@RequestParam(required = true,name = "nombre") String nombre){
        return "Hola a todos me llamo : "+nombre;
    }

    @GetMapping("/api/v1/book")
    public List<Book> getAllBooks(){

        Book book = new Book(1L,"1984"," George Orwell");
        Book book2 = new Book(2L,"El principito","Antoine de Saint-Exupéry");
        Book book3 = new Book(3L,"El señor de los anillos","J.R.R. Tolkien");

        return List.of(book,book2,book3);
    }

}
