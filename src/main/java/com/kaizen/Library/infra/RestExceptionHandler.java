package com.kaizen.Library.infra;

import com.kaizen.Library.DTOS.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice

public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> handleGenericException (Exception exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity ClientNotFound () {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CLIENT NOT FOUND");
//    }


}
