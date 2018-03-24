package com.svlugovoy.springrestbooks.repository;

import com.svlugovoy.springrestbooks.model.Book;
import com.svlugovoy.springrestbooks.model.Books;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private final Map<Integer, Book> books = new HashMap<Integer, Book>(){{
        put(1, new Book(1, "Smith", "Good Book", 2015));
        put(2, new Book(2, "Bloch", "Good Java", 2010));
        put(3, new Book(3, "Ivanov", "Good Day", 2001));
    }};

    private int counter = 3;

    @Override
    public Optional<Book> findById(int id) {
        return Optional.ofNullable(books.get(id));
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }

    @Override
    public Books findAllXml() {
        List<Book> bookList = new ArrayList<>(books.values());
        Books books = new Books();
        books.setBooks(bookList);
        return books;
    }

    @Override
    public void save(Book book) {
        if (book.getId() == 0) {
            counter++;
            book.setId(counter);
            books.put(counter, book);
            System.out.println("*** Book with id=" + book.getId() + " was created");
        } else {
            books.put(book.getId(), book);
            System.out.println("*** Book with id=" + book.getId() + " was updated");
        }
    }

    @Override
    public boolean delete(int id) {
        if (!books.containsKey(id)) {
            return false;
        }

        books.remove(id);
        System.out.println("*** Book with id=" + id + " was deleted");
        return true;
    }

}
