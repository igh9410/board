package com.springboot.board.exception;

import com.springboot.board.exception.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PostValidationException.class)
    public String handlePostException(PostValidationException exc, Model theModel) {
        theModel.addAttribute("error", exc.getMessage());
        // return ResponseEntity

        return "errors/post-error";
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String handleMissingParameterException(MissingServletRequestParameterException exc) {
        return "errors/access-denied";
    }


    /*
    // Add an exception handler for AccessDeniedException
    @ExceptionHandler(AccessDeniedException.class)
    @RequestMapping("/errors")
    public String handleAccessDeniedException(AccessDeniedException exc) {
        // return ResponseEntity

        return "errors/access-denied";
    } */

    /*
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CustomErrorResponse> handleException(RuntimeException exc) {


        CustomErrorResponse error = new CustomErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exc.getMessage(),
                System.currentTimeMillis());

        // return ResponseEntity

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    } */

    


}
