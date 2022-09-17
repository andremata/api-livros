package com.andremata.libraryapi.api.resource;

import com.andremata.libraryapi.api.dto.BookDTO;
import com.andremata.libraryapi.api.model.Book;
import com.andremata.libraryapi.api.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private BookService service;
    private ModelMapper modelMapper;

    public BookController(BookService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO create(@RequestBody BookDTO dto) {
        Book book = modelMapper.map(dto, Book.class);
        book = service.save(book);

        return modelMapper.map(book, BookDTO.class);
    }
}
