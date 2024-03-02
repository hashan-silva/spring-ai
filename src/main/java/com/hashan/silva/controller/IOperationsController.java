package com.hashan.silva.controller;

import com.hashan.silva.model.Analysis;
import com.hashan.silva.util.Constant;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.NotImplementedException;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Constant.AI)
@OpenAPIDefinition(tags = {@Tag(name = "API Operations", description = "API Operations with openAI")})
public interface IOperationsController {

    @GetMapping(Constant.GENERATE_ANALYSIS)
    @RouterOperation(operation = @Operation(summary = "Generate Analysis", description = "API for generate analysis for case study"), beanClass = OperationsController.class, beanMethod = "generateAnalysis")
    default ResponseEntity<Analysis> generateAnalysis(){
        throw new NotImplementedException("API not Implemented");
    }

}
