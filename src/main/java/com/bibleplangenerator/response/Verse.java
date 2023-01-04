package com.bibleplangenerator.response;

import lombok.Data;

@Data
public class Verse {
    private String book_id;
    private String book_name;
    private int chapter;
    private int verse;
    private String text;
}
