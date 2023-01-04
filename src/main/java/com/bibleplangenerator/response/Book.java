package com.bibleplangenerator.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    private String name;
    private int numberOfChapters;

    public static Book of(String name, int numberOfChapters) {
        return new Book(name, numberOfChapters);
    }
}
