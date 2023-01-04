package com.bibleplangenerator.response;

import lombok.Data;

@Data
public class Reference {
    private String startingBook;
    private String endingBook;
    private int startingChapter;
    private int endingChapter;
    private int startingVerse;
    private int endingVerse;

    @Override
    public String toString() {
        if (startingBook.equals(endingBook)) {
            if (startingChapter == endingChapter) {
                return String.format("%s %d:%d-%d", startingBook, startingChapter, startingVerse, endingVerse);
            } else {
                return String.format("%s %d:%d-%d:%d", startingBook, startingChapter, startingVerse, endingChapter, endingVerse);
            }
        } else {
            return String.format("%s %d:%d - %s %d:%d", startingBook, startingChapter, startingVerse, endingBook, endingChapter, endingVerse);
        }
    }
}
