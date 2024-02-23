package com.hashan.silva.service.impl;

import com.hashan.silva.model.Poetry;
import com.hashan.silva.service.PoetryService;

public class PoetryServiceImpl implements PoetryService {
    @Override
    public String generateHaiku() {
        return PoetryService.super.generateHaiku();
    }

    @Override
    public Poetry generatePoetry(String genre, String theme) {
        return PoetryService.super.generatePoetry(genre, theme);
    }
}
