package com.global.commtech.test.anagramfinder;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class AnagramGroupServiceTest {
    @Test
    void shouldTestEmptyInput() {
        AnagramGroupService service = new AnagramGroupService();
        List<String> words = Collections.emptyList();
        Map<String, List<String>> result = service.groupAnagrams(words);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldTestSingleWord() {
        AnagramGroupService service = new AnagramGroupService();
        List<String> words = Collections.singletonList("word");
        Map<String, List<String>> result = service.groupAnagrams(words);

        assertEquals(1, result.size());
        assertTrue(result.values().stream().anyMatch(group -> group.contains("word")));
    }

    @Test
    void shouldTestAllDifferentWords() {
        AnagramGroupService service = new AnagramGroupService();
        List<String> words = Arrays.asList("abc", "def", "ghi");
        Map<String, List<String>> result = service.groupAnagrams(words);

        assertEquals(3, result.size());
        assertTrue(result.values().stream().anyMatch(group -> group.contains("abc")));
        assertTrue(result.values().stream().anyMatch(group -> group.contains("def")));
        assertTrue(result.values().stream().anyMatch(group -> group.contains("ghi")));
    }

    @Test
    void shouldTestDuplicateWords() {
        AnagramGroupService service = new AnagramGroupService();
        List<String> words = Arrays.asList("abc", "abc", "bac");
        Map<String, List<String>> result = service.groupAnagrams(words);

        assertEquals(1, result.size());
        assertTrue(result.values().stream().anyMatch(group -> group.containsAll(Arrays.asList("abc", "abc", "bac"))));
    }

    @Test
    void shouldTestCaseInsensitiveWords() {
        AnagramGroupService service = new AnagramGroupService();
        List<String> words = Arrays.asList("abc", "BAC", "cba");
        Map<String, List<String>> result = service.groupAnagrams(words);

        assertEquals(1, result.size());
        assertTrue(result.values().stream().anyMatch(group -> group.containsAll(Arrays.asList("abc", "BAC", "cba"))));
    }

    @Test
    void shouldTestLongWords() {
        AnagramGroupService service = new AnagramGroupService();
        List<String> words = Arrays.asList("characteristic", "charsarcteitic", "acricarstetich");
        Map<String, List<String>> result = service.groupAnagrams(words);

        assertEquals(1, result.size());
        assertTrue(result.values().stream().anyMatch(group -> group.containsAll(Arrays.asList("characteristic", "charsarcteitic", "acricarstetich"))));
    }

    @Test
    void shouldTestSpecialCharacters() {
        AnagramGroupService service = new AnagramGroupService();
        List<String> words = Arrays.asList("a!b@c#", "#c@b!a", "b!@a#c");
        Map<String, List<String>> result = service.groupAnagrams(words);

        assertEquals(1, result.size());
        assertTrue(result.values().stream().anyMatch(group -> group.containsAll(Arrays.asList("a!b@c#", "#c@b!a", "b!@a#c"))));
    }

    @Test
    void shouldTestMixedCaseWords() {
        AnagramGroupService service = new AnagramGroupService();
        List<String> words = Arrays.asList("Listen", "Silent", "enlist", "inlets", "tinsel", "LiStEn");
        Map<String, List<String>> result = service.groupAnagrams(words);

        assertEquals(1, result.size());
        assertTrue(result.values().stream().anyMatch(group -> group.containsAll(Arrays.asList("Listen", "Silent", "enlist", "inlets", "tinsel", "LiStEn"))));
    }

    @Test
    void shouldTestMultipleWordGroups() {
        AnagramGroupService service = new AnagramGroupService();
        List<String> words = Arrays.asList("abc", "bac", "cba", "unf", "fun");
        Map<String, List<String>> result = service.groupAnagrams(words);

        assertEquals(2, result.size());
        assertTrue(result.values().stream().anyMatch(group -> group.containsAll(Arrays.asList("abc", "bac", "cba"))));
        assertTrue(result.values().stream().anyMatch(group -> group.containsAll(Arrays.asList("unf", "fun"))));
    }

    @Test
    void shouldTestSpecialCharactersWordGroups() {
        AnagramGroupService service = new AnagramGroupService();
        List<String> words = Arrays.asList("a!b", "b!a", "c!d", "d!c");
        Map<String, List<String>> result = service.groupAnagrams(words);

        assertEquals(2, result.size());
        assertTrue(result.values().stream().anyMatch(group -> group.containsAll(Arrays.asList("a!b", "b!a"))));
        assertTrue(result.values().stream().anyMatch(group -> group.containsAll(Arrays.asList("c!d", "d!c"))));
    }
}
