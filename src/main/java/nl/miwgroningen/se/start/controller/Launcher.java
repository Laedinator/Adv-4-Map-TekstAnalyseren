package nl.miwgroningen.se.start.controller;

import nl.miwgroningen.se.start.model.WordLinesMap;
import nl.miwgroningen.se.start.model.WordMap;
import nl.miwgroningen.se.start.model.WordSet;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * <p>
 * Lees een tekst bestand in en analyseer het
 */
public class Launcher {

    public static void main(String[] args) {
        WordSet wordSet = new WordSet();
        WordMap wordMap = new WordMap();
        WordLinesMap wordLinesMap = new WordLinesMap();

//        wordSetLaunch(wordSet);
//        wordMapLaunch(wordMap);
        wordLinesMapLaunch(wordLinesMap);


    }

    private static void wordLinesMapLaunch(WordLinesMap wordLinesMap) {
        String source = "src/main/resources/MaxHavelaar.txt";
        wordLinesMap.readFromFile(source);
        String wordToFind = "zwygen";

        for (String word : wordLinesMap.getWordsSorted()) {
            System.out.println(word);
        }
        System.out.printf("\nThe file: %s, does have %d unique words.\n", source, wordLinesMap.getNrOfUniqueWords());
        System.out.printf("\nThe word %s is found on the following lines:\n", wordToFind);
        for (Integer lines : wordLinesMap.getWordLineNrs(wordToFind)) {
            System.out.print(lines + ", ");
        }
    }

    private static void wordMapLaunch(WordMap wordMap) {
        String source = "src/main/resources/MaxHavelaar.txt";
        String wordToCheck = "zyns";
        wordMap.readFromFile(source);
        for (String word : wordMap.getWordsSorted()) {
            System.out.println(word);
        }
        System.out.printf("The file: %s,\ndoes have %d unique words.\n", source, wordMap.getNrOfUniqueWords());
        System.out.printf("The word %s has %d occurrences.", wordToCheck, wordMap.getWordCount(wordToCheck));
    }

    private static void wordSetLaunch(WordSet wordSet) {
        wordSet.readFromFile("src/main/resources/MaxHavelaar.txt");
        System.out.println(wordSet.getNrOfUniqueWords());
        for (String word : wordSet.getWordsSorted()) {
            System.out.println(word);
        }
    }
}
