package med.taleko.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController     //crea un controlador de rest
@RequestMapping ("/hello")
public class HelloController {
    @GetMapping // con esta anotacion se mapea la ruta "/hello" para esta aplicacion
    public String helloWorld() {
        return "entendiendo spring boot!";
    }
}
