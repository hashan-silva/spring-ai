package com.hashan.silva.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hashan.silva.model.Poetry;
import com.hashan.silva.service.PoetryService;
import com.hashan.silva.util.Constant;
import org.springframework.ai.client.AiClient;
import org.springframework.ai.client.AiResponse;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.ai.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PoetryServiceImpl implements PoetryService {

    @Autowired
    private AiClient aiClient;

    @Override
    public String generateHaiku() {
        return aiClient.generate(Constant.WRITE_ME_HAIKU_ABOUT_CAT);
    }

    @Override
    public Poetry generatePoetry(String genre, String theme) {
        BeanOutputParser<Poetry> poetryBeanOutputParser = new BeanOutputParser<>(Poetry.class);
        String promptString = """
                Write me {genre} poetry about {theme}
                {format} 
                """;

        PromptTemplate promptTemplate = new PromptTemplate(promptString);
        promptTemplate.add("genre", genre);
        promptTemplate.add("theme", theme);
        promptTemplate.add("format", poetryBeanOutputParser.getFormat());

        promptTemplate.setOutputParser(poetryBeanOutputParser);

        AiResponse aiResponse = aiClient.generate(promptTemplate.create());

        return parsePoetryFromJson(aiResponse.getGeneration().getText());
    }

    private Poetry parsePoetryFromJson(String jsonResponse) {
        try {
            return new ObjectMapper().readValue(jsonResponse, Poetry.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
