package nl.ordina.wordfrequency.helper;

import nl.ordina.wordfrequency.model.WordFrequency;

import java.util.Comparator;

public class SortByAscendingComparator implements Comparator<WordFrequency> {
    @Override
    public int compare(WordFrequency frequency,WordFrequency frequency1 ) {
        return frequency.getWord().compareTo(frequency1.getWord());
    }
}
