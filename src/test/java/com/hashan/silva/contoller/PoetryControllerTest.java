package com.hashan.silva.contoller;

import com.hashan.silva.SpringAIApplication;
import com.hashan.silva.controller.IPoetryController;
import com.hashan.silva.controller.PoetryController;
import com.hashan.silva.model.Poetry;
import com.hashan.silva.service.PoetryService;
import com.hashan.silva.util.Constant;
import org.apache.commons.lang3.NotImplementedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PoetryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PoetryService poetryService;


    @Test
    public void testGenerateHaiku() throws Exception {

        when(poetryService.generateHaiku()).thenReturn("Generated cat haiku");

        // Create an instance of the PoetryController with the mocked PoetryService
        PoetryController poetryController = new PoetryController(poetryService);

        this.mockMvc = MockMvcBuilders.standaloneSetup(poetryController).build();

        this.mockMvc.perform(get("/"+Constant.AI + "/" + Constant.GENERATE_HAIKU))
                .andExpect(status().isOk())
                .andExpect(content().string(containsStringIgnoringCase("cat")));

    }

    @Test
    public void testGenerateHaiku_Exception(){
        IPoetryController poetryController = mock(IPoetryController.class);
        when(poetryController.generateHaiku()).thenThrow(new NotImplementedException("API not Implemented"));
        assertThrows(NotImplementedException.class, poetryController::generateHaiku);
    }

    @Test
    public void testGeneratePoetry() throws Exception {
        Poetry poetry = new Poetry("Title","Generated poetry text","genre","theme");
        when(poetryService.generatePoetry(anyString(), anyString())).thenReturn(poetry);
        String genre = "lyric";
        String theme = "coffee";
        PoetryController poetryController = new PoetryController(poetryService);

        this.mockMvc = MockMvcBuilders.standaloneSetup(poetryController).build();

        this.mockMvc.perform(get("/" + Constant.AI + "/" + Constant.GENERATE_POETRY)
                .param("genre", genre)
                .param("theme", theme))
                .andExpect(status().isOk());

    }

    @Test
    public void testGeneratePoetry_Exception(){
        IPoetryController poetryController = mock(IPoetryController.class);
        when(poetryController.generatePoetry(anyString(), anyString())).thenThrow(new NotImplementedException("API not Implemented"));
        assertThrows(NotImplementedException.class, ()->poetryController.generatePoetry("genre","theme"));
    }
}
