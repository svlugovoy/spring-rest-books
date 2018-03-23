package com.svlugovoy.springrestbooks.repository;

import com.svlugovoy.springrestbooks.model.Book;
import com.svlugovoy.springrestbooks.model.Books;

import java.util.List;

public interface BookRepository {

    Book findById(int id);

    List<Book> findAll();

    void save(Book book);

    boolean delete(int id);

    Books findAllXml();
}
