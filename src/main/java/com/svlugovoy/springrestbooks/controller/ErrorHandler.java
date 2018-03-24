package com.svlugovoy.springrestbooks.controller;

import com.svlugovoy.springrestbooks.exception.BookNotFoundException;
import com.svlugovoy.springrestbooks.model.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    protected ResponseEntity<Book> handleException(
            BookNotFoundException ex, WebRequest request) {
        System.out.println("Book not found! my_header=" + request.getHeader("my_header"));
        return ResponseEntity.notFound().build();
    }
}
