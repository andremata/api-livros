package com.andremata.libraryapi.api.resource;

import com.andremata.libraryapi.api.dto.BookDTO;
import com.andremata.libraryapi.api.model.Book;
import com.andremata.libraryapi.api.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class BookControllerTest {

    static String BOOK_API = "/api/books";

    @Autowired
    MockMvc mock;

    @MockBean
    BookService service;

    @Test
    @DisplayName("Deve criar um livro")
    public void createBookTest() throws Exception {
        BookDTO dto = BookDTO.builder().id(101).title("Livro Teste").author("Autor").isbn("123456789").build();
        Book book = Book.builder().id(101).title("Livro Teste").author("Autor").isbn("123456789").build();

        BDDMockito.given(service.save(Mockito.any(Book.class))).willReturn(book);

        String json = new ObjectMapper().writeValueAsString(dto);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(BOOK_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mock.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(dto.getId()))
                .andExpect(jsonPath("title").value(dto.getTitle()))
                .andExpect(jsonPath("author").value(dto.getAuthor()))
                .andExpect(jsonPath("isbn").value(dto.getIsbn()));
    }

    @Test
    @DisplayName("Deve dar erro ao tentar criar um livro sem dados")
    public void createInvalidBookTest() {

    }
}
