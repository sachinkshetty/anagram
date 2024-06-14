package com.global.commtech.test.anagramfinder;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnagramGroupService {

    public Map<String, List<String>> groupAnagrams(List<String> words) {
        Map<String, List<String>> anagramMap = new HashMap<>();

        for (String word : words) {
            String key = generateKey(word);
            anagramMap.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
        }

        return anagramMap;
    }

    private String generateKey(String word) {
        int[] charCount = new int[256];
        for (char c : word.toLowerCase().toCharArray()) {
            charCount[c]++;
        }
        return Arrays.toString(charCount);
    }
}