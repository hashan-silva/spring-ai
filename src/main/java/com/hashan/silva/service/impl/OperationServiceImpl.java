package com.hashan.silva.service.impl;

import com.hashan.silva.model.Analysis;
import com.hashan.silva.service.OperationService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
public class OperationServiceImpl implements OperationService {

    private final OpenAiChatClient openAiChatClient;

    @Autowired
    public OperationServiceImpl(OpenAiChatClient openAiChatClient) {
        this.openAiChatClient = openAiChatClient;
    }

    @Override
    public Analysis generateAnalysis() {
        String fileName = "prompt.txt";
        String promptString = this.getPromptString(fileName);

        BeanOutputParser<Analysis> analysisBeanOutputParser = new BeanOutputParser<>(Analysis.class);
        PromptTemplate promptTemplate = new PromptTemplate(promptString);
        promptTemplate.add("format", analysisBeanOutputParser.getFormat());

        promptTemplate.setOutputParser(analysisBeanOutputParser);

        ChatResponse aiResponse = this.openAiChatClient.call(promptTemplate.create());

        return analysisBeanOutputParser.parse(aiResponse.getResult().getOutput().getContent());

    }

    private String getPromptString(String fileName) {
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
            if (inputStream == null) {
                throw new FileNotFoundException("File not found: " + fileName);
            }
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
