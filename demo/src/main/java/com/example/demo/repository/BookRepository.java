package com.example.demo.repository;

import com.example.demo.model.Book;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BookRepository {

    private final Map<Long, Book> storage = new HashMap<>();
    private long idCounter = 1;

    public Book save(Book book) {
        if (book.getId() == null) book.setId(idCounter++);
        storage.put(book.getId(), book);
        return book;
    }

    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public List<Book> findAll() {
        return new ArrayList<>(storage.values());
    }

    public List<Book> findAll(int page, int size, String sortBy,
                              String sortDir, String titleFilter, Long categoryId) {
        List<Book> result = new ArrayList<>(storage.values());

        if (titleFilter != null && !titleFilter.isBlank()) {
            result = result.stream()
                    .filter(b -> b.getTitle().toLowerCase()
                            .contains(titleFilter.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (categoryId != null) {
            result = result.stream()
                    .filter(b -> categoryId.equals(b.getCategoryId()))
                    .collect(Collectors.toList());
        }

        Comparator<Book> comparator = switch (sortBy) {
            case "author" -> Comparator.comparing(Book::getAuthor);
            case "price"  -> Comparator.comparing(Book::getPrice);
            default       -> Comparator.comparing(Book::getTitle);
        };
        if ("desc".equalsIgnoreCase(sortDir)) comparator = comparator.reversed();
        result.sort(comparator);

        int from = page * size;
        if (from >= result.size()) return List.of();
        return result.subList(from, Math.min(from + size, result.size()));
    }

    public boolean existsByTitle(String title) {
        return storage.values().stream()
                .anyMatch(b -> b.getTitle().equalsIgnoreCase(title));
    }

    public void deleteById(Long id) { storage.remove(id); }
    public boolean existsById(Long id) { return storage.containsKey(id); }
}