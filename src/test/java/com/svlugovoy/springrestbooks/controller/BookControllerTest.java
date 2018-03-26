package com.svlugovoy.springrestbooks.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.svlugovoy.springrestbooks.SpringRestBooksApplication;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes=SpringRestBooksApplication.class)
public class BookControllerTest {
    @Autowired
    private WebApplicationContext applicationContext;

    private MockMvc mockMvc;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @BeforeAll
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(
                applicationContext).build();
    }

    @Test
    public void test() {
        assertTrue(true);
    }

}
