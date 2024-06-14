package com.global.commtech.test.anagramfinder;

import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class FileProcessorTest {

    private FileProcessor fileProcessor;
    private Path tempDir;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;

    @BeforeEach
    void setUp() throws IOException {
        fileProcessor = new FileProcessor();
        tempDir = Files.createTempDirectory("fileProcessorTest");
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.walk(tempDir)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);

        System.setOut(standardOut);

    }

    @Test
    void testReadWordsFromFile() throws IOException {
        Path tempFile = createTempFileWithLines("testfile.txt", "word1", "word2", "word3");

        List<String> words = fileProcessor.readWordsFromFile(tempFile.toString());

        assertEquals(Arrays.asList("word1", "word2", "word3"), words);
    }

    @Test
    void testReadWordsFromFile_FileNotFound() {
        String nonExistentFilePath = tempDir.resolve("nonexistentfile.txt").toString();

        Exception exception = assertThrows(FileNotFoundException.class, () -> {
            fileProcessor.readWordsFromFile(nonExistentFilePath);
        });

        assertTrue(exception.getMessage().contains("nonexistentfile.txt"));
    }

    @Test
    void testReadWordsFromFile_EmptyFile() throws IOException {
        Path tempFile = createTempFileWithLines("emptyfile.txt");

        List<String> words = fileProcessor.readWordsFromFile(tempFile.toString());

        assertTrue(words.isEmpty());
    }

    @Test
    void testWriteGroupedAnagrams() {
        Map<String, List<String>> groupedAnagrams = new HashMap<>();
        groupedAnagrams.put("abc", Arrays.asList("abc", "bac", "cab"));
        groupedAnagrams.put("fun", Arrays.asList("fun", "unf"));

        fileProcessor.writeGroupedAnagrams(groupedAnagrams);

        String expectedOutput = "abc,bac,cab\nfun,unf";
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    void testWriteGroupedAnagrams_EmptyMap() {
        Map<String, List<String>> groupedAnagrams = Collections.emptyMap();

        fileProcessor.writeGroupedAnagrams(groupedAnagrams);

        assertTrue(outputStreamCaptor.toString().trim().isEmpty());
    }

    private Path createTempFileWithLines(String filename, String... lines) throws IOException {
        Path tempFile = tempDir.resolve(filename);
        Files.write(tempFile, Arrays.asList(lines));
        return tempFile;
    }
}