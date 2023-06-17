package com.info.javajediprimerapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //Anotacion a nivel de clase
public class BookController {

    //Get --> Obtener
    // http://localhost:8080/aplicacion/v1/despedida
    @GetMapping("/aplicacion/v1/despedida")  //Anotacion a nivel de metodo
    public String goodByeWorld(){
        return "Adios mundo cruel";
    }

    //http://localhost:8080/aplicacion/v1/saludo?nombre=Ian
    @GetMapping("/aplicacion/v1/saludo")
    public String helloWorld(@RequestParam(required = true,name = "nombre") String nombre){
        //@RequestParam(required = true,name = "nombre") String nombre Anotacion a nivel de atributo
        return "Hello " + nombre;
    }

}
