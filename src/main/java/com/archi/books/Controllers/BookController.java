package com.archi.books.Controllers;

import com.archi.books.Entity.Book;
import com.archi.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @PutMapping(path = "/books/{isbn}")
    public ResponseEntity<Book> createBook(@PathVariable final String isbn, @RequestBody final Book book) {
        book.setIsbn(isbn);
        final Book savedbook=bookService.create(book);
        final ResponseEntity<Book> response =new ResponseEntity<Book>(savedbook ,HttpStatus.CREATED);
        return response;
    }

    @GetMapping(path = "/books/{isbn}")
    public ResponseEntity<Book> retrieveBook(@PathVariable final String isbn){
        final Optional<Book> foundbook=bookService.findById(isbn);
        return foundbook.map(book -> new ResponseEntity<Book>(book,HttpStatus.OK)).
                orElse(new ResponseEntity<Book>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "/books")
    public ResponseEntity<List<Book>> listbooks(){
       return  new ResponseEntity<List<Book>>(bookService.listbooks(), HttpStatus.OK);

    }

}
