package com.app.dictionary.Controller;

import com.app.dictionary.Service.WordService;
import com.app.dictionary.api.response.WordResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class WordController {

    @Autowired
    private WordService wordService;

    @GetMapping("/{word}")
    public ResponseEntity<List<WordResponse>> wordMeaning(@PathVariable("word") String word){
        try {
        // Fetch all meanings once
        List<WordResponse> wordResponses = wordService.getWordMeaning(word);
        return new ResponseEntity<>(wordResponses,HttpStatus.OK);

       } catch (HttpClientErrorException.NotFound e) {
            // Handle 404 Not Found specifically
            return ResponseEntity.status(404).body(Collections.emptyList());
        } catch (Exception e) {
            // Handle other exception
            return ResponseEntity.status(500).body(Collections.emptyList());
        }
    }
}
