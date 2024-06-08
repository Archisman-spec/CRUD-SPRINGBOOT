package com.archi.books.service.Impl;

import com.archi.books.Entity.Book;
import com.archi.books.Entity.BookEntity;
import com.archi.books.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.archi.books.Testdata.testbook;
import static com.archi.books.Testdata.testbookentity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

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
        final Book result =underTest.save(book);
        assertEquals( book, result);

    }

    @Test
    public void testthatfindbyIdreturnsEmptyWhenNoBook() {
        final String isbn = "123123123";
        when(bookRepository.findById(eq(isbn))).thenReturn(Optional.empty());
        final Optional<Book> result =underTest.findById(isbn);
        assertEquals( Optional.empty(), result);
    }

    @Test
    public void testthatfindbyIdreturnsBookWhenExists() {
        final Book book = testbook();
        final BookEntity bookie = testbookentity();

        when(bookRepository.findById(eq(book.getIsbn()))).thenReturn(Optional.of(bookie));

        final Optional<Book> result =underTest.findById(book.getIsbn());
        assertEquals( Optional.of(book), result);
    }

    @Test
    public void testListBooksReturnsEmptyListWhenNoBooksExist() {
        when(bookRepository.findAll()).thenReturn(new ArrayList<BookEntity>());
        final List<Book> result= underTest.listbooks();
        assertEquals( 0, result.size());
    }

    @Test
    public void testListBooksReturnsBooksWhenBooksExist() {
        final BookEntity bookEntity=testbookentity();
        when(bookRepository.findAll()).thenReturn(List.of(bookEntity));
        final List<Book> result= underTest.listbooks();
        assertEquals( 1, result.size());
    }

    @Test
    public void textbookexistsreturnsfalsewhennobookExist(){
        when(bookRepository.existsById(any())).thenReturn(false);
       final boolean result = underTest.isbookexists(testbook());
        assertEquals(false, result);
    }

 @Test
    public void textbookexistsreturnstruewhenbookdoesExist(){
        when(bookRepository.existsById(any())).thenReturn(true);
       final boolean result = underTest.isbookexists(testbook());
        assertEquals(true, result);
    }

    @Test
    public void testdeletebooksdeletesbook(){
        final String isbn = "132123123";
        underTest.deleteBookById(isbn);
        verify(bookRepository, times(1)).deleteById(eq(isbn));

    }
}
