package com.example.demo.service;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.CreateCategoryRequestDto;
import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Override
    public CategoryDto create(CreateCategoryRequestDto request) {
        Category saved = repository.save(new Category(null, request.getName()));
        return new CategoryDto(saved.getId(), saved.getName());
    }

    @Override
    public List<CategoryDto> getAll() {
        return repository.findAll().stream()
                .map(c -> new CategoryDto(c.getId(), c.getName()))
                .toList();
    }
}