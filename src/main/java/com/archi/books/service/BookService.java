package com.archi.books.service;

import com.archi.books.Entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    boolean isbookexists(Book book);

    Book save(Book book);
    Optional<Book> findById(String isbn);
    List<Book> listbooks();

    void deleteBookById(String isbn);

}
