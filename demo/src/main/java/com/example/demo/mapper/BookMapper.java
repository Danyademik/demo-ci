package com.example.demo.mapper;

import com.example.demo.dto.BookDto;
import com.example.demo.dto.CreateBookRequestDto;
import com.example.demo.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book toModel(CreateBookRequestDto dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPrice(dto.getPrice());
        book.setCategoryId(dto.getCategoryId());
        return book;
    }

    public BookDto toDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPrice(),
                book.getCategoryId()
        );
    }
}