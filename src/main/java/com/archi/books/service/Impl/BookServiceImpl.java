package com.archi.books.service.Impl;

import com.archi.books.Entity.Book;
import com.archi.books.Entity.BookEntity;
import com.archi.books.repositories.BookRepository;
import com.archi.books.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public boolean isbookexists(Book book) {
      return   bookRepository.existsById(book.getIsbn());
    }

    @Override
    public Book save(final Book book) {
        final BookEntity bookEntity = booktobookentity(book);
        final BookEntity savedbook=bookRepository.save(bookEntity);
        return bookentitytobook(savedbook);
    }

    @Override
    public Optional<Book> findById(String isbn) {
        final Optional<BookEntity> foundbook=bookRepository.findById(isbn);
        return foundbook.map(book -> bookentitytobook(book) );
    }

    @Override
    public List<Book> listbooks() {
       final List<BookEntity> foundbooks =bookRepository.findAll();
       return foundbooks.stream().map(book-> bookentitytobook(book)).collect(Collectors.toList());
    }

    @Override
    public void deleteBookById(String isbn) {
       try {
           bookRepository.deleteById(isbn);
       }catch (final EmptyResultDataAccessException ex){
           log.debug("Attempted to delete non-existing book",ex);
       }

    }

    private BookEntity booktobookentity(Book book) {
       return BookEntity.builder()
               .isbn(book.getIsbn())
               .title(book.getTitle())
               .author(book.getAuthor()).build();
    }
    private Book bookentitytobook(BookEntity book) {
       return Book.builder()
               .isbn(book.getIsbn())
               .title(book.getTitle())
               .author(book.getAuthor()).build();
    }
}
