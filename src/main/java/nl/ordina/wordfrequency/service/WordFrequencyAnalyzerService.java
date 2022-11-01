package nl.ordina.wordfrequency.service;

import nl.ordina.wordfrequency.helper.SortByAscendingComparator;
import nl.ordina.wordfrequency.helper.SortByFrequencyComparator;
import nl.ordina.wordfrequency.model.WordFrequency;
import nl.ordina.wordfrequency.model.WordFrequencyAnalyzer;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

import static java.lang.Math.toIntExact;

@Service

public class WordFrequencyAnalyzerService implements WordFrequencyAnalyzer
{

    private WordFrequency[] wordFrequencyCollection(String text){
        var words = List.of(text.toLowerCase().trim().split("\\W+"));
        if (words.size() <= 0){
            return new WordFrequency[]{};
        }

        var filteredWords = words.stream().distinct().toArray();
        WordFrequency[] arr = new WordFrequency[0];
        var wordFrequencies = new ArrayList<WordFrequency>();
        for (Object filteredWord : filteredWords) {
            var word = filteredWord.toString();
            int count = 0;
            for (int j = 0; j < (long) words.size(); j++) {
                if (word.equals(words.toArray()[j])) {
                    count++;
                }
            }

            int frequency = count;
            var wordFrequency = new WordFrequency() {
                @Override
                public String getWord() {
                    return word;
                }

                @Override
                public int getFrequency() {
                    return frequency;
                }
            };
            wordFrequencies.add(wordFrequency);
        }

        return wordFrequencies.toArray(arr);
    }
    @Override
    public int calculateHighestFrequency(String text) {
        return Arrays.stream(wordFrequencyCollection(text))
                .max(Comparator.comparing(WordFrequency::getFrequency)).get().getFrequency();
    }

    @Override
    public int calculateFrequencyForWord(String text, String word) {
        return Arrays.stream(wordFrequencyCollection(text))
                .filter((x-> x.getWord().toLowerCase().equals(word)))
                .map(WordFrequency::getFrequency).findFirst().orElse(null);
    }

    @Override
    public WordFrequency[] calculateMostFrequentNWords(String text, int n) {
        var wordFrequencies = wordFrequencyCollection(text);
        SortByFrequencyComparator sortByFrequencyComparator = new SortByFrequencyComparator();
        SortByAscendingComparator sortByAscendingComparator = new SortByAscendingComparator();
        var frequencies =
                 Arrays.stream(wordFrequencies)
                .sorted(sortByFrequencyComparator)
                .limit(n)
                .sorted(sortByAscendingComparator)
                         .sorted(sortByFrequencyComparator)
                .toList();
        return frequencies.toArray(new WordFrequency[0]);
    }


}
