package com.example.autocomplete.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    public Map<Character, TrieNode> children = new HashMap<>();
    public boolean isEndOfWord = false;
    public int frequency = 0; // Add this line for frequency
} 