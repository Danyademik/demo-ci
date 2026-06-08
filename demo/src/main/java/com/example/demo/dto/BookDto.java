package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private Double price;
    private Long categoryId;
}