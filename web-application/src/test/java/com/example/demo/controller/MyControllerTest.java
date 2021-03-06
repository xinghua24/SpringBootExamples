package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(MyController.class)
public class MyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnMessage() throws Exception{
        this.mockMvc.perform(get("/greeting/hello"))
                .andExpect(status().isOk());
    }
}
