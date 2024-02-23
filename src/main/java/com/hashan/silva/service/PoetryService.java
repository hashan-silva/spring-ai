package com.hashan.silva.service;

import com.hashan.silva.model.Poetry;
import org.apache.commons.lang3.NotImplementedException;

public interface PoetryService {
    default public String generateHaiku(){
        throw new NotImplementedException("Not Implemented");
    }

    default Poetry generatePoetry(String genre, String theme){
        throw new NotImplementedException("Not Implemented");
    }
}
