package com.course.course.fourth;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;


class TrieNode {
    Map<Character, TrieNode> children;
    String translation;

    public TrieNode() {
        children = new HashMap<>();
        translation = null;
    }
}

public class Translator {
    private TrieNode root;
    private static final String DICTIONARY = "src/main/java/com/course/course/fourth/dictionary.txt";

    public void readDictionary() throws InvalidFileFormatException, FileReadException {
        root = new TrieNode();
        try (BufferedReader br = new BufferedReader(new FileReader(Translator.DICTIONARY))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length != 2) {
                    throw new InvalidFileFormatException("Invalid dictionary format in file: " + Translator.DICTIONARY);
                }
                insert(root, parts[0].trim().toLowerCase(), parts[1].trim());
            }
        } catch (IOException e) {
            throw new FileReadException("Error reading file: " + Translator.DICTIONARY);
        }
    }

    private void insert(TrieNode root, String key, String value) {
        TrieNode current = root;
        for (char c : key.toCharArray()) {
            current = current.children.computeIfAbsent(c, k -> new TrieNode());
        }
        current.translation = value;
    }

    public String translateFile(String filePath) throws FileReadException {
        StringBuilder result = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                result.append(translatePhrase(line));
            }
        } catch (IOException e) {
            throw new FileReadException("Error reading file: " + filePath);
        }
        return result.toString();
    }

    private String translatePhrase(String line) {
        StringBuilder translated = new StringBuilder();
        int i = 0;
        while (i < line.length()) {
            TrieNode current = root;
            int j = i;
            int lastTranslatedIndex = -1;
            String lastTranslation = null;
            while (j < line.length() && current.children.containsKey(line.charAt(j))) {
                current = current.children.get(line.charAt(j));
                if (current.translation != null) {
                    lastTranslatedIndex = j;
                    lastTranslation = current.translation;
                }
                j++;
            }
            if (lastTranslation != null) {
                translated.append(lastTranslation);
                i = lastTranslatedIndex + 1;
            } else {
                translated.append(line.charAt(i));
                i++;
            }
        }
        return translated.toString();
    }

    public void addWordToDictionary(String word, String translation) {
        try {
            Files.write(Paths.get("src/main/java/com/course/course/fourth/dictionary.txt"),
                    (word + " | " + translation + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Error updating the dictionary file: " + e.getMessage());
        }
        readDictionary();
    }
}