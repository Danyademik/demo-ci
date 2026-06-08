package com.example.demo.service;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.CreateCategoryRequestDto;

import java.util.List;

public interface CategoryService {
    CategoryDto create(CreateCategoryRequestDto request);
    List<CategoryDto> getAll();
}