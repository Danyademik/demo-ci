package com.example.demo.service;

import com.example.demo.dto.BookDto;
import com.example.demo.dto.CreateBookRequestDto;

import java.util.List;

public interface BookService {
    BookDto create(CreateBookRequestDto request);
    BookDto getById(Long id);
    List<BookDto> getAll(int page, int size, String sortBy,
                         String sortDir, String titleFilter, Long categoryId);
    BookDto update(Long id, CreateBookRequestDto request);
    void delete(Long id);
}