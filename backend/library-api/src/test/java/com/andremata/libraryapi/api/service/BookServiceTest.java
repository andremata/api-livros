package com.andremata.libraryapi.api.service;

import com.andremata.libraryapi.api.model.Book;
import com.andremata.libraryapi.api.repository.BookRepository;
import com.andremata.libraryapi.api.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class BookServiceTest {

    BookService service;

    @MockBean
    BookRepository repository;

    @BeforeEach
    public void setUp() {
        this.service = new BookServiceImpl(repository);
    }

    @Test
    @DisplayName("Deve salvar um livro")
    public void saveBookTest() {
        Book book = Book.builder()
                .title("Livro Teste")
                .author("Autor")
                .isbn("123456789")
                .build();

        Mockito.when(repository.save(book)).thenReturn(Book.builder()
                .id(101)
                .title("Livro Teste")
                .author("Autor")
                .isbn("123456789")
                .build());

        Book savedBook = service.save(book);

        assertThat(savedBook.getId()).isNotNull();
        assertThat(savedBook.getTitle()).isEqualTo("Livro Teste");
        assertThat(savedBook.getAuthor()).isEqualTo("Autor");
        assertThat(savedBook.getIsbn()).isEqualTo("123456789");
    }
}
