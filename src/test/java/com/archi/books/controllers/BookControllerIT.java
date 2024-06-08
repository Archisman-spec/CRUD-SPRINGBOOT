package com.archi.books.controllers;


import com.archi.books.Entity.Book;
import com.archi.books.Testdata;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.archi.books.Testdata.testbook;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class BookControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testBookisCreated() throws Exception {
        final Book book = Testdata.testbook();
        final ObjectMapper mapper = new ObjectMapper();
        final String bookJson= mapper.writeValueAsString(book);

        mockMvc.perform(MockMvcRequestBuilders.put("/books/" + book.getIsbn()).content("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(book.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(book.getAuthor()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(book.getTitle())
                );
    }
}
