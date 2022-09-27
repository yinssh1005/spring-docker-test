package com.example.springdockertest;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class SpringDockerTestApplicationTests {

//    @Autowired
//    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void anotherTesting() {
        System.out.println("done");

    }

//    @Test
//    public void whenTestApp_thenEmptyResponse() throws Exception {
//        this.mockMvc.perform(get("/foos")
//                .andExpect(status().isOk())
//                .andExpect(...);
//    }

}
