package com.example.autocomplete.trie;

import java.util.*;
import java.util.stream.Collectors;

public class Trie {
    private final TrieNode root = new TrieNode();

    public void insert(String word, int frequency) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node = node.children.computeIfAbsent(c, k -> new TrieNode());
        }
        node.isEndOfWord = true;
        node.frequency = frequency;
    }

    public void insert(String word) {
        insert(word, 1);
    }

    public List<String> getSuggestions(String prefix) {
        List<WordFreq> results = new ArrayList<>();
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            node = node.children.get(c);
            if (node == null) return new ArrayList<>();
        }
        dfs(node, new StringBuilder(prefix), results);
        results.sort((a, b) -> Integer.compare(b.freq, a.freq));
        return results.stream().map(wf -> wf.word).collect(Collectors.toList());
    }

    private void dfs(TrieNode node, StringBuilder prefix, List<WordFreq> results) {
        if (node.isEndOfWord) results.add(new WordFreq(prefix.toString(), node.frequency));
        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            prefix.append(entry.getKey());
            dfs(entry.getValue(), prefix, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    private static class WordFreq {
        String word;
        int freq;
        WordFreq(String word, int freq) { this.word = word; this.freq = freq; }
    }

    // Fuzzy search (Levenshtein distance)
    public List<String> getFuzzySuggestions(String input, int maxDistance) {
        List<WordFreq> results = new ArrayList<>();
        fuzzyDfs(root, new StringBuilder(), input, maxDistance, results);
        results.sort((a, b) -> Integer.compare(b.freq, a.freq));
        return results.stream().map(wf -> wf.word).collect(Collectors.toList());
    }

    private void fuzzyDfs(TrieNode node, StringBuilder current, String target, int maxDist, List<WordFreq> results) {
        if (node.isEndOfWord && levenshtein(current.toString(), target) <= maxDist) {
            results.add(new WordFreq(current.toString(), node.frequency));
        }
        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            current.append(entry.getKey());
            fuzzyDfs(entry.getValue(), current, target, maxDist, results);
            current.deleteCharAt(current.length() - 1);
        }
    }

    private int levenshtein(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i <= a.length(); i++) dp[i][0] = i;
        for (int j = 0; j <= b.length(); j++) dp[0][j] = j;
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1],
                                    Math.min(dp[i - 1][j], dp[i][j - 1]));
            }
        }
        return dp[a.length()][b.length()];
    }
} 