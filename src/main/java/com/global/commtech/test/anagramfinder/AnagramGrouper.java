package com.global.commtech.test.anagramfinder;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class AnagramGrouper {

    private final AnagramGroupService anagramGroupService;
    private final FileProcessor fileProcessor;

    public AnagramGrouper(AnagramGroupService anagramGroupService, FileProcessor fileProcessor) {
        this.anagramGroupService = anagramGroupService;
        this.fileProcessor = fileProcessor;
    }

    public void groupAnagrams(String filePath) throws IOException {
        List<String> words = fileProcessor.readWordsFromFile(filePath);
        Map<String, List<String>> groupedAnagrams = anagramGroupService.groupAnagrams(words);
        fileProcessor.writeGroupedAnagrams(groupedAnagrams);
    }
}