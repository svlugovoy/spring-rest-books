package com.svlugovoy.springrestbooks.repository;

import com.svlugovoy.springrestbooks.model.Book;
import com.svlugovoy.springrestbooks.model.Books;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Optional<Book> findById(int id);

    List<Book> findAll();

    void save(Book book);

    boolean delete(int id);

    Books findAllXml();
}
