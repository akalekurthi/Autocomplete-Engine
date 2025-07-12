package com.example.autocomplete.service;

import com.example.autocomplete.trie.Trie;
import org.springframework.stereotype.Service;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import java.io.*;
import java.util.List;

@Service
public class TrieService implements ApplicationListener<ContextRefreshedEvent> {
    private final Trie trie = new Trie();
    private boolean initialized = false;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!initialized) {
            try {
                init();
                initialized = true;
            } catch (IOException e) {
                throw new RuntimeException("Failed to initialize TrieService", e);
            }
        }
    }

    private void init() throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream("words.txt")))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().toLowerCase().split("\\s+");
                if (parts.length == 2) {
                    trie.insert(parts[0], Integer.parseInt(parts[1]));
                } else {
                    trie.insert(parts[0], 1); // default frequency
                }
            }
        }
    }

    public List<String> getSuggestions(String prefix) {
        return trie.getSuggestions(prefix.toLowerCase());
    }

    public List<String> getFuzzySuggestions(String prefix, int maxDistance) {
        return trie.getFuzzySuggestions(prefix.toLowerCase(), maxDistance);
    }

    public void addWord(String word, int frequency) {
        trie.insert(word.toLowerCase(), frequency);
    }
} 