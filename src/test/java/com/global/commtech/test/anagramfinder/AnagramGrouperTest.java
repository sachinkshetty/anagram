package com.global.commtech.test.anagramfinder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnagramGrouperTest {


    private final AnagramGroupService anagramGroupService = mock(AnagramGroupService.class);

    private final FileProcessor fileProcessor = mock(FileProcessor.class);

    @InjectMocks
    private AnagramGrouper anagramGrouper;

    @BeforeEach
    void setUp() {
        anagramGrouper = new AnagramGrouper(anagramGroupService, fileProcessor);
    }

    @Test
    void testShouldGroupAnagramsSuccessfully() throws IOException {
        String filePath = "testfile.txt";
        List<String> words = Arrays.asList("abc", "bac", "cba", "fun", "unf");
        Map<String, List<String>> groupedAnagrams = Map.of(
                "abc", Arrays.asList("abc", "bac", "cba"),
                "fun", Arrays.asList("fun", "unf")
        );

        when(fileProcessor.readWordsFromFile(filePath)).thenReturn(words);
        when(anagramGroupService.groupAnagrams(words)).thenReturn(groupedAnagrams);

        anagramGrouper.groupAnagrams(filePath);

        verify(fileProcessor).readWordsFromFile(filePath);
        verify(anagramGroupService).groupAnagrams(words);
        verify(fileProcessor).writeGroupedAnagrams(groupedAnagrams);
    }

    @Test
    void testShouldThrowExceptionForNonExistentFile() throws IOException {
        String filePath = "nonexistentfile.txt";

        when(fileProcessor.readWordsFromFile(filePath)).thenThrow(new IOException("File not found"));

        Exception exception = assertThrows(IOException.class, () -> {
            anagramGrouper.groupAnagrams(filePath);
        });

        assertEquals("File not found", exception.getMessage());
        verify(fileProcessor).readWordsFromFile(filePath);
        verify(anagramGroupService, never()).groupAnagrams(anyList());
        verify(fileProcessor, never()).writeGroupedAnagrams(anyMap());
    }
}
