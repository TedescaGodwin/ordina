package nl.ordina.wordfrequency.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nl.ordina.wordfrequency.model.WordFrequency;
import nl.ordina.wordfrequency.service.WordFrequencyAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Api
public class WordController {

    final private WordFrequencyAnalyzerService _frequencyService;

    @Autowired
    public WordController(WordFrequencyAnalyzerService frequency) {
        _frequencyService = frequency;
    }

    @ApiOperation("Calculate Highest Frequency")
    @GetMapping(value = "/calculateHighestFrequency")
    public int calculateHighestFrequency(String text) throws Exception {
        return _frequencyService.calculateHighestFrequency(text);
    }

    @ApiOperation("Calculate Frequency For Word")
    @GetMapping(value = "/calculateFrequencyForWord")
    public int calculateFrequencyForWord(String text, String word) throws Exception{
        return _frequencyService.calculateFrequencyForWord(text, word);
    }

    @ApiOperation("Calculate Most Frequency N Words")
    @GetMapping(value = "/calculateMostFrequentNWords")
    public WordFrequency[] calculateMostFrequentNWords(String text, int n) throws Exception{
        return _frequencyService.calculateMostFrequentNWords(text, n);
    }
}
