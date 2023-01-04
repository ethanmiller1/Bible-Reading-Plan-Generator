package com.bibleplangenerator.response;

import lombok.Data;

import java.util.List;

@Data
public class Response {
    private String reference;
    private List<Verse> verses;
    private String text;
    private String translation_id;
    private String translation_name;
    private String translation_note;

}
