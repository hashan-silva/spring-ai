package com.hashan.silva.contoller;

import com.hashan.silva.model.Poetry;
import com.hashan.silva.service.PoetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PoetryController implements IPoetryController {

    private final PoetryService poetryService;

    @Autowired
    public PoetryController(PoetryService poetryService) {
        this.poetryService = poetryService;
    }

    @Override
    public ResponseEntity<String> generateHaiku() {
        return new ResponseEntity<>(poetryService.generateHaiku(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Poetry> generatePoetry(String genre, String theme) {
        return new ResponseEntity<>(poetryService.generatePoetry(genre, theme), HttpStatus.OK);
    }
}
