package nl.ordina.wordfrequency.controller;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.ordina.wordfrequency.service.WordFrequencyAnalyzerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

@SpringBootTest
@AutoConfigureMockMvc
public class WordControllerTest {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    WordFrequencyAnalyzerService service;

    private String text = "Your  $25 AWS Credit Code is listed below. " +
            "Visit the AWS Credits Redemption Page to apply the " +
            "promotional credit code to your AWS account.",
            word = "credit";
    private int frequency = 1;

    @Test
    public void itShouldCalculateHighestFrequency() throws Exception {
        //Given -> Text {sentence}

        //when
        ResultActions resultActions = mockMvc
                .perform(get("/calculateHighestFrequency")
                        .contentType(MediaType.APPLICATION_ATOM_XML_VALUE)
                        .content(objectMapper.writeValueAsString(text)));
        //then
        resultActions.andExpect(status().isOk());
        var frequency = service.calculateHighestFrequency(text);
        assertThat(frequency).isGreaterThan(0);
    }
  
    @Test
    public void isShouldcalculateFrequencyForWord() throws Exception {
        //Given -> Text {sentence}


        //when
        var resultActions = mockMvc
                .perform(get("/calculateFrequencyForWord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(text))
                        .content(objectMapper.writeValueAsString(word)));

        //then
         resultActions.andExpect(status().isOk());
        var listOfFrequentWords = service.calculateFrequencyForWord(text, word);
        assertThat(frequency).isGreaterThan(0);
    }

    @Test
    public void itShouldCalculateMostFrequentNWords() throws Exception {
        //Given -> Text {sentence}


        //when
        var resultActions = mockMvc
                .perform(get("/calculateMostFrequentNWords")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(text))
                        .content(objectMapper.writeValueAsString(frequency)));

        //then
        resultActions.andExpect(status().isOk());
        var listOfFrequentWords = service.calculateMostFrequentNWords(text, frequency);
        assertThat(Arrays.stream(listOfFrequentWords)).containsSequence();
    }
}