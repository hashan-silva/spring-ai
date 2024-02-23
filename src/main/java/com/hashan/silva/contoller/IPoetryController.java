package com.hashan.silva.contoller;

import com.hashan.silva.model.Poetry;
import com.hashan.silva.util.Constant;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.NotImplementedException;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constant.AI)
@OpenAPIDefinition(tags = {@Tag(name = "API Operations", description = "API Operations with openAI")})
public interface IPoetryController {

    @GetMapping(Constant.GENERATE_HAIKU)
    @RouterOperation(operation = @Operation(summary = "Generate Haiku", description = "API for generate Haiku"), beanClass = PoetryController.class, beanMethod = "generateHaiku")
    default public ResponseEntity<String> generateHaiku() {
        throw new NotImplementedException("API not Implemented");
    }

    @GetMapping(Constant.GENERATE_POETRY)
    @RouterOperation(operation = @Operation(summary = "Generate Poetry", description = "API for generate poetry"), beanClass = PoetryController.class, beanMethod = "generatePoetry")
    default public ResponseEntity<Poetry> generatePoetry(
            @RequestParam("genre") String genre,
            @RequestParam("theme") String theme) {
        throw new NotImplementedException("API not Implemented");
    }

}
