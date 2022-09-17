package com.andremata.libraryapi.api.service.impl;

import com.andremata.libraryapi.api.model.Book;
import com.andremata.libraryapi.api.repository.BookRepository;
import com.andremata.libraryapi.api.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book save(Book book) {
        return repository.save(book);
    }
}
