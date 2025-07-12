package com.example.autocomplete.controller;

import com.example.autocomplete.service.TrieService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class AutocompleteController {
    private final TrieService trieService;

    public AutocompleteController(TrieService trieService) {
        this.trieService = trieService;
    }

    @GetMapping("/autocomplete")
    public List<String> autocomplete(@RequestParam String prefix) {
        return trieService.getSuggestions(prefix);
    }

    @GetMapping("/autocomplete/fuzzy")
    public List<String> fuzzyAutocomplete(@RequestParam String prefix, @RequestParam(defaultValue = "1") int maxDistance) {
        return trieService.getFuzzySuggestions(prefix, maxDistance);
    }

    @PostMapping("/autocomplete/add")
    public String addWord(@RequestParam String word, @RequestParam(defaultValue = "1") int frequency) {
        trieService.addWord(word, frequency);
        return "Word added: " + word + " (frequency: " + frequency + ")";
    }
} 