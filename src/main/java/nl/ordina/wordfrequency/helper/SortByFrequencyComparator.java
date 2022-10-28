package nl.ordina.wordfrequency.helper;

import nl.ordina.wordfrequency.model.WordFrequency;

import java.util.Comparator;

public class SortByFrequencyComparator implements Comparator<WordFrequency> {
    @Override
    public int compare(WordFrequency o1, WordFrequency o2) {
        return Integer.compare(o2.getFrequency(), (o1.getFrequency()));
    }
}

