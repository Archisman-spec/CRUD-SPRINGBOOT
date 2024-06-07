package com.archi.books.service.Impl;

import com.archi.books.Entity.Book;
import com.archi.books.Entity.BookEntity;
import com.archi.books.repositories.BookRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {
    @InjectMocks
    private BookServiceImpl underTest;
    
    @Mock
    private BookRepository bookRepository;

    public void testthatbookisSaved(){
        Book book = Book.builder()
                .isbn("109325u0234").
                author("Archi the rand").
                title("randi chodne ki kitab").build();

        BookEntity bookie = BookEntity.builder()
                .isbn("109325u0234").
                author("Archi the rand").
                title("randi chodne ki kitab").build();


        when(bookRepository.save(bookie)).thenReturn(bookie);
        final Book result =underTest.create(book);

    }

}
