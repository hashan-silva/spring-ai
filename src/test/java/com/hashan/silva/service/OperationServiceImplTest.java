package com.hashan.silva.service;

import com.hashan.silva.model.Analysis;
import com.hashan.silva.model.Poetry;
import com.hashan.silva.service.impl.OperationServiceImpl;
import com.hashan.silva.service.impl.PoetryServiceImpl;
import org.apache.commons.lang3.NotImplementedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class OperationServiceImplTest {

    @Mock
    private OpenAiChatClient aiClient;
    @Test
    public void testGenerateAnalysis() {
        String generatedAnalysisString = "{\n" +
                "  \"responses\": [\n" +
                "    {\n" +
                "      \"answer\": \"Financial aspects, strategic fit, impact on team and company vision, legacy in the industry\",\n" +
                "      \"question\": \"what should be taken into account when making the decission?\"\n" +
                "     }\n" +
                "  ],\n" +
                "  \"summary\": \"Analysis of the case study\"\n" +
                "}";

        List<Generation> generations = new ArrayList<>();
        generations.add(new Generation(generatedAnalysisString));
        ChatResponse aiResponse = new ChatResponse(generations);
        when(aiClient.call((Prompt) any())).thenReturn(aiResponse);

        OperationService operationService = new OperationServiceImpl(aiClient);
        Analysis generateAnalysis = operationService.generateAnalysis();
        assertEquals("Analysis of the case study", generateAnalysis.summary());
    }

    @Test
    public void testGenerateAnalysis_Exception() {
        OperationService operationService = mock(OperationService.class);
        when(operationService.generateAnalysis()).thenThrow(new NotImplementedException("Not Implemented"));
        assertThrows(NotImplementedException.class, () -> operationService.generateAnalysis());
    }
}