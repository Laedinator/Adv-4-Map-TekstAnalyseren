package nl.miwgroningen.se.start.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author Marc Ledermann
 * <m.ledermann@st.hanze.nl>
 * Purpose of the program:
 * Stores words with the numbers of occurances.
 **/
public class WordMap {
    Map<String, Integer> wordMap = new HashMap<>();

    public void readFromFile(String filenaam) {
        File sourceFile = new File(filenaam);

        try (Scanner sourceReader = new Scanner(sourceFile)) {
            while (sourceReader.hasNext()) {
                String word = sourceReader.next();
                word = word.replaceAll("[^A-Za-z0-9]", "");
                if (!word.isEmpty()) {
                    wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }

    public int getNrOfUniqueWords() {
        return wordMap.size();
    }

    public Iterable<String> getWordsSorted() {
        List<String> wordList = new ArrayList<>();
        for (Map.Entry<String, Integer> word : wordMap.entrySet()) {
            wordList.add(word.getKey() + " (" + word.getValue() + ")");
        }

        wordList.sort(String.CASE_INSENSITIVE_ORDER);
        return wordList;
    }

    public int getWordCount(String word) {

        return wordMap.get(word);
    }


}
