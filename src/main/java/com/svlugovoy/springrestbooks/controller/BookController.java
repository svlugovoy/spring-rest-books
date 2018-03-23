package com.svlugovoy.springrestbooks.controller;

import com.svlugovoy.springrestbooks.model.Book;
import com.svlugovoy.springrestbooks.model.Books;
import com.svlugovoy.springrestbooks.repository.BookRepository;
import org.springframework.http.MediaType;
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
    public Book findBookById(@PathVariable int id) {
        return bookRepository.findById(id);
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
    public void updateBook(@PathVariable int id, @RequestBody Book book) {
        Book item = bookRepository.findById(id);
        item.setId(id);
        bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id) {
        bookRepository.delete(id);
    }

}
