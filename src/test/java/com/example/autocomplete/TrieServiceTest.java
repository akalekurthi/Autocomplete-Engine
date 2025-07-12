package com.example.autocomplete;

import com.example.autocomplete.service.TrieService;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TrieServiceTest {
    @Test
    public void testAutocomplete() {
        TrieService service = new TrieService();
        List<String> suggestions = service.getSuggestions("wea");
        assertTrue(suggestions.contains("weather"));
        assertTrue(suggestions.contains("wealth"));
    }
} 