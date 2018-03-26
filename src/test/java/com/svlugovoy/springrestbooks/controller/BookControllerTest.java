package com.svlugovoy.springrestbooks.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.svlugovoy.springrestbooks.SpringRestBooksApplication;
import com.svlugovoy.springrestbooks.model.Book;
import com.svlugovoy.springrestbooks.repository.BookRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig(SpringRestBooksApplication.class)
@AutoConfigureMockMvc
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @BeforeEach
    public void setup() {
        Book book1 = new Book(1, "Smith", "Good Book", 2015);
        Book book2 = new Book(2, "Bloch", "Good Java", 2010);
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));
        when(bookRepository.findById(3)).thenReturn(Optional.empty());
        when(bookRepository.findById(1)).thenReturn(Optional.of(book1));
    }

    @Test
    public void findBooks_StorageNonEmpty_TwoBooksReturned_Json() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/book"));
        assertAll(
                () -> result.andExpect(status().isOk()),
                () -> result.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)),
                () -> result.andExpect(jsonPath("$", Matchers.hasSize(2))),
                () -> result.andExpect(jsonPath("$.[0].name", Matchers.equalTo("Good Book"))),
                () -> result.andExpect(jsonPath("$.[1].name", Matchers.equalTo("Good Java")))
        );
    }

    @Test
    public void findBookById_OneBookReturned_Xml() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/book/1").accept(MediaType.APPLICATION_XML_VALUE));
        assertAll(
                () -> result.andExpect(status().isOk()),
                () -> result.andExpect(content().contentType(MediaType.APPLICATION_XML))
        );
    }

    @Test
    public void findBookById_InvalidId_NotFoundReturned() throws Exception {
        mockMvc.perform(get("/api/book/3"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findBookById_InvalidId_BadRequestReturned() throws Exception {
        mockMvc.perform(get("/api/book/4asd"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void findBookById_ValidId_BookIsReturned() throws Exception {
        mockMvc.perform(get("/api/book/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.equalTo("Good Book")));
    }

}
