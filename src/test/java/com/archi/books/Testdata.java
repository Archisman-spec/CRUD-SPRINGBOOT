package com.archi.books;

import com.archi.books.Entity.Book;
import com.archi.books.Entity.BookEntity;

public final class Testdata {

    private Testdata(){
    }

    public static Book testbook(){
        return Book.builder()
                .isbn("109325u0234").
                author("Archi the rand").
                title("randi chodne ki kitab").build();

    }
    public static BookEntity testbookentity(){
        return  BookEntity.builder()
                .isbn("109325u0234").
                author("Archi the rand").
                title("randi chodne ki kitab").build();
    }
}
