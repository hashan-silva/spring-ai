package com.hashan.silva.service.impl;

import com.hashan.silva.model.Poetry;
import com.hashan.silva.service.PoetryService;
import com.hashan.silva.util.Constant;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PoetryServiceImpl implements PoetryService {

    private final OpenAiChatClient aiClient;

    @Autowired
    public PoetryServiceImpl(OpenAiChatClient aiClient) {
        this.aiClient = aiClient;
    }

    @Override
    public String generateHaiku() {
        return this.aiClient.call(Constant.WRITE_ME_HAIKU_ABOUT_CAT);
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

        ChatResponse aiResponse = this.aiClient.call(promptTemplate.create());

        return poetryBeanOutputParser.parse(aiResponse.getResult().getOutput().getContent());
    }
}
