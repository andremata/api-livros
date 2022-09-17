package com.andremata.libraryapi.api.repository;

import com.andremata.libraryapi.api.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
