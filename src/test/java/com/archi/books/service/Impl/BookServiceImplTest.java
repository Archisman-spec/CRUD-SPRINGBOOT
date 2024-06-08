package com.archi.books.service.Impl;

import com.archi.books.Entity.Book;
import com.archi.books.Entity.BookEntity;
import com.archi.books.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.archi.books.Testdata.testbook;
import static com.archi.books.Testdata.testbookentity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {
    @InjectMocks
    private BookServiceImpl underTest;
    
    @Mock
    private BookRepository bookRepository;

    @Test
    public void testthatbookisSaved(){
      final  Book book = testbook();

        BookEntity bookie = testbookentity();

        when(bookRepository.save(bookie)).thenReturn(bookie);
        final Book result =underTest.create(book);
        assertEquals( book, result);

    }

}
