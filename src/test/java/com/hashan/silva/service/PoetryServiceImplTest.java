package com.hashan.silva.service;

import com.hashan.silva.model.Poetry;
import com.hashan.silva.service.PoetryService;
import com.hashan.silva.service.impl.PoetryServiceImpl;
import com.hashan.silva.util.Constant;
import org.apache.commons.lang3.NotImplementedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.ai.client.AiClient;
import org.springframework.ai.client.AiResponse;
import org.springframework.ai.client.Generation;
import org.springframework.ai.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class PoetryServiceImplTest {

    @Mock
    private AiClient aiClient;

    @Test
    public void testGenerateHaiku_Exception() {
        PoetryService poetryService1 = mock(PoetryService.class);
        when(poetryService1.generateHaiku()).thenThrow(new NotImplementedException("Not Implemented"));
        assertThrows(NotImplementedException.class, poetryService1::generateHaiku);
    }

    @Test
    public void testGeneratePoetry_Exception() {
        PoetryService poetryService1 = mock(PoetryService.class);
        when(poetryService1.generatePoetry(anyString(), anyString())).thenThrow(new NotImplementedException("Not Implemented"));
        assertThrows(NotImplementedException.class, () -> poetryService1.generatePoetry("genre", "theme"));
    }

    @Test
    public void testGenerateHaiku() {
        when(aiClient.generate(Constant.WRITE_ME_HAIKU_ABOUT_CAT)).thenReturn("Haiku about cat");
        PoetryService poetryService = new PoetryServiceImpl(aiClient);
        String response = poetryService.generateHaiku();
        assertEquals("Haiku about cat", response);
        verify(aiClient).generate(Constant.WRITE_ME_HAIKU_ABOUT_CAT);
    }

    @Test
    public void testGeneratePoetry() {
        String generatedPoetryString = "{\n" +
                "  \"genre\": \"Liric poetry\",\n" +
                "  \"poetry\": \"Fire dances in the night\",\n" +
                "  \"theme\": \"Fire\",\n" +
                "  \"title\": \"Dance of Flames\"\n" +
                "}";
        List<Generation> generations = new ArrayList<>();
        generations.add(new Generation(generatedPoetryString));
        AiResponse aiResponse = new AiResponse(generations);
        when(aiClient.generate((Prompt) any())).thenReturn(aiResponse);

        PoetryService poetryService = new PoetryServiceImpl(aiClient);
        Poetry generatedPoetry = poetryService.generatePoetry("genre", "theme");
        assertEquals("Fire dances in the night", generatedPoetry.poetry());

    }
}