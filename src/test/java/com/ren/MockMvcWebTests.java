package com.ren;

import com.ren.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author qiang.ren
 * @version 1.0
 * @since 2020/8/26 10:05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MockMvcWebTests {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void testHomePage() throws Exception{
        mockMvc.perform(get("/readingList/ren"))
                .andExpect(status().isOk())
                .andExpect(view().name("readingList"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("books", is(empty())));
    }

    @Test
    public void testAddBook() throws Exception{
        mockMvc.perform(post("/readingList/ren")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("title", "book tile")
        .param("author", "book author")
        .param("isbn", "1234567890")
        .param("description", "book description"));

        Book book = new Book();
        book.setId(1L);
        book.setReader("ren");
        book.setTitle("book tile");
        book.setAuthor("book author");
        book.setIsbn("1234567890");
        book.setDescription("book description");

        mockMvc.perform(get("/readingList/ren"))
                .andExpect(status().isOk())
                .andExpect(view().name("readingList"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("books", hasSize(1)))
                .andExpect(model().attribute("books", contains(samePropertyValuesAs(book))));
    }
}
