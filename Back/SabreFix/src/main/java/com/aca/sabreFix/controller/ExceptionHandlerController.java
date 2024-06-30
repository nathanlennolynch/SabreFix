package com.aca.sabreFix.controller;

import com.aca.sabreFix.model.ExceptionResponse;
import com.aca.sabreFix.model.WeaponException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(WeaponException.class)
    public ResponseEntity<ExceptionResponse> handleMovie(WeaponException weaponException,
                                                         HttpServletRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage(weaponException.getMessage());
        exceptionResponse.setRequestURI(request.getRequestURI());

        return ResponseEntity.badRequest().body(exceptionResponse);
    }
}
