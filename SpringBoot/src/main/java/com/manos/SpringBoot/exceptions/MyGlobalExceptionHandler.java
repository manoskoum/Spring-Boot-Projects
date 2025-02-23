package com.manos.SpringBoot.exceptions;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MyGlobalExceptionHandler {

    //χειριζεται μονο MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    //Map που περιεχει validation errors
    public ResponseEntity<Map<String,String>> myMethodArgumentNotValidException(MethodArgumentNotValidException e){
        //αποθηκευονται τα σφαλματα
        Map<String,String> response = new HashMap<>();
        //παιρνει ολα τα errors απο getBindingResult//forEach τα επεξεργαζεται ενα ενα
        e.getBindingResult().getAllErrors().forEach(err->{
           //μετατρεπει τo err σε fieldERROR Για να παρει το ονομα του πεδιου που απετυχε
            String fieldName=((FieldError) err).getField();
            //παιρνει το default μηνυμα λαθους
            String message=err.getDefaultMessage();
            response.put(fieldName,message);
        });
                return new ResponseEntity<Map<String,String>> (response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> myResourceNotFoundException(ResourceNotFoundException e) {
        String message = e.getMessage();
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(ApiException.class)
    //custom class
    public ResponseEntity <String> myApiException(ApiException e){
        String message=e.getMessage();
        return new ResponseEntity<String>(message,HttpStatus.BAD_REQUEST);
    }
}
