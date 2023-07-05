package com.info.javajediprimerapp.controller;

import com.info.javajediprimerapp.domain.Book;
import com.info.javajediprimerapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController //Anotacion a nivel de clase
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

        return bookService.getAllBooks();
    }

    //POST --> Crear un recurso
    @PostMapping("/api/v1/book")
    public Book createBook(@RequestBody Book book){
        return bookService.createBook(book);
    }

    //PUT --> Actualizar un recurso
    @PutMapping("/api/v1/book/{idBook}")
    public String updateBook(@PathVariable(value = "idBook")UUID idBook,@RequestBody Book bookUpdated){
        Optional<Book> book = bookService.updateBook(idBook,bookUpdated);

        if(book.isEmpty()){
            System.out.println("Book not found");
            return "Book not found";
        }else {
            System.out.println("Book updated");
            return "/api/v1/book/"+book.get().getUuid();
        }
    }

    //Delete --> Eliminar un recurso
    @DeleteMapping("/api/v1/book/{idBook}")
    public String deleteBook(@PathVariable(value = "idBook")UUID idBook){
        boolean isBookDeleted = bookService.deleteBook(idBook);

        if(isBookDeleted){
            System.out.println("Book deleted");
            return "Book deleted";
        }else {
            System.out.println("Book not found");
            return "Book not found";
        }
    }


}
