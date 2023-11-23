package nl.miwgroningen.se.start.model;

import java.io.File;
import java.util.*;

/**
 * @author Marc Ledermann
 * <m.ledermann@st.hanze.nl>
 * Purpose of the program:
 **/
public class WordLinesMap {
    Map<String, List<Integer>> wordMap = new HashMap<>();

    public void readFromFile(String filenaam) {
        File sourceFile = new File(filenaam);
        try (Scanner sourceReader = new Scanner(sourceFile)) {
            int indexLine = 1;
            while (sourceReader.hasNextLine()) {
                String stringFromLine = sourceReader.nextLine();
                String[] wordFromLine = stringFromLine.split("\\s+");
                addWords(wordFromLine, indexLine);
                indexLine++;
            }
        } catch (Exception e) {
            System.err.println("Could not find the file: " + e.getMessage());
        }
    }

    private void addWords(String[] wordFromLine, int indexLine) {
        for (String word : wordFromLine) {
            word = word.replaceAll("[^A-Za-z0-9]", "");
            if (!word.isEmpty()) {
                if (!wordMap.containsKey(word)) {
                    List<Integer> newIndexList = new ArrayList<>();
                    newIndexList.add(indexLine);
                    wordMap.put(word, newIndexList);
                } else {
                    wordMap.get(word).add(indexLine);
                }
            }
        }
    }

    public int getNrOfUniqueWords() {
        return wordMap.size();
    }

    public Iterable<String> getWordsSorted() {
        List<String> sortedList = new ArrayList<>();
        for (String word : wordMap.keySet()) {
            StringBuilder stringBuilder = new StringBuilder("You find the word " + word + " on the lines:\n");
            for (Integer indexLine : wordMap.get(word)) {
                stringBuilder.append(indexLine).append(", ");
            }
            sortedList.add(stringBuilder.toString());
        }
        sortedList.sort(String.CASE_INSENSITIVE_ORDER);
        return sortedList;
    }

    public List<Integer> getWordLineNrs(String word) {
        return new ArrayList<>(wordMap.get(word));
    }


}
