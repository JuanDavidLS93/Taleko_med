package med.taleko.api.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e){
        var errores = e.getFieldErrors().stream().map(datosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    private record datosErrorValidacion(String campo, String error){
        public datosErrorValidacion(FieldError error){
            this (error.getField(), error.getDefaultMessage());
        }

    }

} //esto es como decir ExceptionHandler cuando identifiques en este restControllerAdvice  el
// EntityNotFoundException lanza el codigo 404 not found.
