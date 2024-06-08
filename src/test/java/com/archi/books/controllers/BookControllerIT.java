package com.archi.books.controllers;


import com.archi.books.Entity.Book;
import com.archi.books.Testdata;
import com.archi.books.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BookControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookService bookService;

    @Test
    public void testBookisCreatedReturnsHTTP200() throws Exception {
        final Book book = Testdata.testbook();
        final ObjectMapper mapper = new ObjectMapper();
        final String bookJson= mapper.writeValueAsString(book);

        mockMvc.perform(MockMvcRequestBuilders.put("/books/" + book.getIsbn()).content("application/json").
                        contentType(MediaType.APPLICATION_JSON).content(bookJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(book.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value(book.getAuthor()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(book.getTitle())
                );
    }

    @Test
    public void testBookisUpdatedReturnsHTTP201() throws Exception {
        final Book book = Testdata.testbook();
        bookService.save(book);
        book.setAuthor("Archi the rand");

        final ObjectMapper mapper = new ObjectMapper();
        final String bookJson= mapper.writeValueAsString(book);

        mockMvc.perform(MockMvcRequestBuilders.put("/books/" + book.getIsbn()).content("application/json").
                        contentType(MediaType.APPLICATION_JSON).content(bookJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(book.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value(book.getAuthor()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(book.getTitle())
                );
    }

    @Test
    public void testthatretrievebookreturns404WhennotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/123123123"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testthatretrievebookreturnsHttp200AndBookWhenExists() throws Exception {
        final Book book = Testdata.testbook();
        bookService.save(book);

        mockMvc.perform(MockMvcRequestBuilders.get("/books/" + book.getIsbn()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(book.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value(book.getAuthor()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(book.getTitle()));

    }

    @Test
    public void testthatlistbookreturns200emptyListwhennoBookExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testthatlistbookreturns200bookwhenBookExists() throws Exception {
        final Book book = Testdata.testbook();
        bookService.save(book);
        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].isbn").value(book.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].author").value(book.getAuthor()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].title").value(book.getTitle()));
    }

}
