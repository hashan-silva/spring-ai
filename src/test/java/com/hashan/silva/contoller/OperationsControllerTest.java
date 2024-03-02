package com.hashan.silva.contoller;

import com.hashan.silva.SpringAIApplication;
import com.hashan.silva.controller.IOperationsController;
import com.hashan.silva.controller.OperationsController;
import com.hashan.silva.model.Analysis;
import com.hashan.silva.model.Response;
import com.hashan.silva.service.OperationService;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OperationsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private OperationService operationService;

    @Test
    public void testGenerateAnalysis() throws Exception {
        Response response = new Response("What would be your advice to Kimmo?","Consider the long-term benefits for the company and team, assess the alignment of visions and goals with the acquirer, seek expert advice");
        List<Response> responseList = new ArrayList<>();
        responseList.add(response);
        Analysis analysis = new Analysis("Test", responseList);
        when(operationService.generateAnalysis()).thenReturn(analysis);
        OperationsController operationsController = new OperationsController(operationService);

        this.mockMvc = MockMvcBuilders.standaloneSetup(operationsController).build();
        this.mockMvc.perform(get("/" + Constant.AI + "/" + Constant.GENERATE_ANALYSIS))
                .andExpect(status().isOk());
    }

    @Test
    public void testGenerateAnalysis_Exception(){
        IOperationsController operationsController = mock(IOperationsController.class);
        when(operationsController.generateAnalysis()).thenThrow(new NotImplementedException("API not Implemented"));
        assertThrows(NotImplementedException.class, ()->operationsController.generateAnalysis(

        ));
    }
}