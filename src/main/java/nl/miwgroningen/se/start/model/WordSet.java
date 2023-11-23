package nl.miwgroningen.se.start.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * <p>
 * Verwerk een tekst tot een Set van diens woorden
 */
public class WordSet {
    private Set<String> wordSet;

    public WordSet() {
        wordSet = new HashSet<>();
    }

    public void readFromFile(String filenaam) {
        File sourceFile = new File(filenaam);
        try (Scanner sourceReader = new Scanner(sourceFile)) {
            while (sourceReader.hasNext()) {
                String word = sourceReader.next();
                word = word.replaceAll("[^A-Za-z0-9]", "");
                if (!word.isEmpty()) {
                    wordSet.add(word);
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.printf("Could not open %s, it probably does not exist or is protected.%n",
                    filenaam);
        }
    }

    public int getNrOfUniqueWords() {
        return wordSet.size();
    }

    public Iterable<String> getWordsSorted() {
        List<String> wordList = new ArrayList<>(wordSet);
        wordList.sort(String.CASE_INSENSITIVE_ORDER);
        return wordList;
    }
}
