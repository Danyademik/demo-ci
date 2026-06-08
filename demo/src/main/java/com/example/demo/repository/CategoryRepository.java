package com.example.demo.repository;

import com.example.demo.model.Category;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CategoryRepository {

    private final Map<Long, Category> storage = new HashMap<>();
    private long idCounter = 1;

    public Category save(Category category) {
        if (category.getId() == null) category.setId(idCounter++);
        storage.put(category.getId(), category);
        return category;
    }

    public Optional<Category> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public List<Category> findAll() {
        return new ArrayList<>(storage.values());
    }

    public boolean existsById(Long id) {
        return storage.containsKey(id);
    }
}