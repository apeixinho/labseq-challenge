package org.labseq.api;


import org.junit.jupiter.api.Test;
import org.labseq.api.controller.LabSeqController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(LabSeqController.class)
public class LabseqControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getIndex_WithValidInput_ReturnsCorrectSequence() throws Exception {
        mockMvc.perform(get("/api/v1/labseq/0"))
               .andExpect(status().isOk())
               .andExpect(content().string("0"));

        mockMvc.perform(get("/api/v1/labseq/1"))
               .andExpect(status().isOk())
               .andExpect(content().string("1"));

        mockMvc.perform(get("/api/v1/labseq/7"))
               .andExpect(status().isOk())
               .andExpect(content().string("2"));
    }

    @Test
    public void postIndex_WithValidInput_ReturnsCorrectSequence() throws Exception {
        mockMvc.perform(post("/api/v1/labseq/0"))
               .andExpect(status().isOk())
               .andExpect(content().string("0"));

        mockMvc.perform(post("/api/v1/labseq/6"))
               .andExpect(status().isOk())
               .andExpect(content().string("1"));
    }

    @Test
    public void getIndex_WithInvalidInput_ReturnsBadRequest() throws Exception {
        mockMvc.perform(get("/api/v1/labseq/-1"))
               .andExpect(status().isBadRequest());

        mockMvc.perform(get("/api/v1/labseq/20001"))
               .andExpect(status().isBadRequest());
    }

    @Test
    public void postIndex_WithInvalidInput_ReturnsBadRequest() throws Exception {
        mockMvc.perform(post("/api/v1/labseq/-1"))
               .andExpect(status().isBadRequest());

        mockMvc.perform(post("/api/v1/labseq/20001"))
               .andExpect(status().isBadRequest());
    }
}
