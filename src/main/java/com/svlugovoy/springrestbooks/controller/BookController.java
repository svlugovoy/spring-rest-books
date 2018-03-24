package com.svlugovoy.springrestbooks.controller;

import com.svlugovoy.springrestbooks.exception.BookNotFoundException;
import com.svlugovoy.springrestbooks.model.Book;
import com.svlugovoy.springrestbooks.model.Books;
import com.svlugovoy.springrestbooks.repository.BookRepository;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Book> findBookById(@PathVariable String id) {
        int bookId = NumberUtils.toInt(id);
        if(bookId <= 0) {
            return ResponseEntity.badRequest().build(); //400
        }

        Book book = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new); //404
        return ResponseEntity.ok(book); //200
    }

    @GetMapping
    public List<Book> findBooks() {
        return bookRepository.findAll();
    }

    @GetMapping(path = "/xml", produces = {MediaType.APPLICATION_XML_VALUE})
    public Books findBooksXml() {
        return bookRepository.findAllXml();
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
    public void createBook(@RequestBody Book book) {
        bookRepository.save(book);
    }

    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Book> updateBook(@PathVariable String id, @RequestBody Book book) {
        int bookId = NumberUtils.toInt(id);
        if(bookId <= 0) {
            return ResponseEntity.badRequest().build(); //400
        }

        Book item = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new); //404

        book.setId(bookId);
        bookRepository.save(book);

        return ResponseEntity.noContent().build(); //204
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable String id) {
        int bookId = NumberUtils.toInt(id);
        if(bookId <= 0) {
            return ResponseEntity.badRequest().build(); //400
        }

        if(bookRepository.delete(bookId)) {
            return ResponseEntity.noContent().build(); //204
        }
        return ResponseEntity.notFound().build(); //404
    }

}
