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
    @Override
    public int calculateHighestFrequency(String text) {
        List<String> wordArray = List.of(text.toLowerCase().trim().split("\\W+"));
        int count = 0;
        //get unique occurrence
        var filteredList = wordArray.stream().distinct();
        for (String word: filteredList.toList()) {
            Long currentWordCount = wordArray.stream().filter(x-> x.contains(word)).count();
            if(currentWordCount > count) count = toIntExact(currentWordCount);
        }
        return count;
    }

    @Override
    public int calculateFrequencyForWord(String text, String word) {
        return toIntExact(Stream.of(text.toLowerCase().trim().split("\\W+"))
                .filter(x-> x.contains(word.toLowerCase()))
                .count());
    }

    @Override
    public WordFrequency[] calculateMostFrequentNWords(String text, int n) {
        List<String> wordArray = List.of(text.toLowerCase().trim().split("\\W+"));
        WordFrequency[] arr = new WordFrequency[0];
        var wordFrequencies = new ArrayList<WordFrequency>();
        
        var filteredList = wordArray.stream().distinct();
        for (String word: filteredList.toList()) {
            int frequency = toIntExact(wordArray.stream().filter(x -> x.contains(word)).count());
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

        SortByFrequencyComparator sortByFrequencyComparator = new SortByFrequencyComparator();
        SortByAscendingComparator sortByAscendingComparator = new SortByAscendingComparator();

        var frequencies =
                 Arrays.stream(wordFrequencies.toArray(arr))
                .sorted(sortByFrequencyComparator)
                .limit(n)
                .sorted(sortByAscendingComparator)
                         .sorted(sortByFrequencyComparator)
                .toList();

        return frequencies.toArray(arr);
    }


}
