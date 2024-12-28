package com.app.dictionary.api.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true) // Ignore unknown fields to prevent errors
public class WordResponse{
    private String word;
    private ArrayList<Meaning> meanings;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true) // Ignore unknown fields to prevent errors
    public static class Definition{
        private String definition;
//        private ArrayList<Object> synonyms;
//        private ArrayList<Object> antonyms;
//        private String example;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true) // Ignore unknown fields to prevent errors
    public static class Meaning{
        private String partOfSpeech;
        private ArrayList<Definition> definitions;
//        private ArrayList<Object> synonyms;
//        private ArrayList<Object> antonyms;
    }
}






