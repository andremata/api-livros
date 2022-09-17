package com.andremata.libraryapi.api.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private Integer id;
    private String title;
    private String author;
    private String isbn;
}
