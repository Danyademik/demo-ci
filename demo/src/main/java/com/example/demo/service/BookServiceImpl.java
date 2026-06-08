package com.example.demo.service;

import com.example.demo.dto.BookDto;
import com.example.demo.dto.CreateBookRequestDto;
import com.example.demo.mapper.BookMapper;
import com.example.demo.messaging.BookEventPublisher;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final BookMapper mapper;
    private final BookEventPublisher bookEventPublisher;

    @Transactional
    @Override
    public BookDto create(CreateBookRequestDto request) {
        if (repository.existsByTitle(request.getTitle())) {
            throw new RuntimeException(
                    "Book with title '" + request.getTitle() + "' already exists");
        }
        Book saved = repository.save(mapper.toModel(request));
        bookEventPublisher.publishBookCreated(saved.getTitle());
        return mapper.toDto(saved);
    }

    @Override
    public BookDto getById(Long id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Book with id " + id + " not found")));
    }

    @Override
    public List<BookDto> getAll(int page, int size, String sortBy,
                                String sortDir, String titleFilter, Long categoryId) {
        return repository.findAll(page, size, sortBy, sortDir, titleFilter, categoryId)
                .stream().map(mapper::toDto).toList();
    }

    @Transactional
    @Override
    public BookDto update(Long id, CreateBookRequestDto request) {
        if (!repository.existsById(id))
            throw new RuntimeException("Book with id " + id + " not found");
        Book updated = mapper.toModel(request);
        updated.setId(id);
        return mapper.toDto(repository.save(updated));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new RuntimeException("Book with id " + id + " not found");
        repository.deleteById(id);
    }
}