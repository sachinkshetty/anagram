package com.global.commtech.test.anagramfinder;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class FileProcessor {

    public List<String> readWordsFromFile(String filePath) throws IOException {
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.trim());
            }
        }
        return words;
    }

    public void writeGroupedAnagrams(Map<String, List<String>> groupedAnagrams) {
        groupedAnagrams.values().forEach(group -> {
            System.out.println(String.join(",", group));
        });
    }
}
