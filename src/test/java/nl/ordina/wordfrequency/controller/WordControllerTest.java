package nl.ordina.wordfrequency.controller;

import nl.ordina.wordfrequency.service.WordFrequencyAnalyzerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.ServletContext;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class WordControllerTest {

    @Autowired
    private MockMvc mvc;
    private String text = "Your  $25 AWS Credit Code is listed below. " +
            "Visit the AWS Credits Redemption Page to apply the " +
            "promotional credit code to your AWS account.",
            word = "credit";
    private Integer frequency = 1;

    @Test
    void itShouldCalculateHighestFrequency() throws Exception {
        //Given

        RequestBuilder request = MockMvcRequestBuilders.get(String.format("/calculateHighestFrequency?text=%s",text));
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals(2, result.getResponse());
    }

    @Test
    void isShouldCalculateMostFrequentNWords() throws Exception {
        //Given
        RequestBuilder request = MockMvcRequestBuilders.get(String.format("/calculateFrequencyForWord?text=%s&word=%s",text, word));
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals(2, result.getResponse());
    }

    @Test
    void itShouldCalculateMostFrequentNWords() throws Exception {
        //Given
        RequestBuilder request = MockMvcRequestBuilders.get(String.format("/calculateMostFrequentNWords?text=%s&frequency=%s",text, frequency));
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals(1, result.getResponse());
    }
}