package com.hashan.silva.controller;

import com.hashan.silva.model.Analysis;
import com.hashan.silva.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OperationsController implements IOperationsController {

    private final OperationService operationService;

    @Autowired
    public OperationsController(OperationService operationService) {
        this.operationService = operationService;
    }

    @Override
    public ResponseEntity<Analysis> generateAnalysis() {
        return new ResponseEntity<>(operationService.generateAnalysis(), HttpStatus.OK);
    }
}
