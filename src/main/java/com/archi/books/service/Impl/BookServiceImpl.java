package com.archi.books.service.Impl;

import com.archi.books.Entity.Book;
import com.archi.books.Entity.BookEntity;
import com.archi.books.repositories.BookRepository;
import com.archi.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book create(final Book book) {
        final BookEntity bookEntity = booktobookentity(book);
        final BookEntity savedbook=bookRepository.save(bookEntity);
        return bookentitytobook(savedbook);
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
