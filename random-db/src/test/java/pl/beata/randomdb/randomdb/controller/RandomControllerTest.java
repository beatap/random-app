package pl.beata.randomdb.randomdb.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@AutoConfigureMockMvc
public class RandomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String baseUrl = "/randomdb";

    @Test
    public void should_random_number() throws Exception {
        mockMvc.perform(get(baseUrl))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
        ;
    }

    @Test
    public void should_random_number_by_id() throws Exception{
        mockMvc.perform(get(baseUrl + "/{id}",20))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    public void should_add_random_number() throws Exception {
        mockMvc.perform(post(baseUrl)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{number: 45}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void should_not_add_random_number() throws Exception {
        mockMvc.perform(post(baseUrl)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{number: }"))
                .andExpect(status().isInternalServerError());
    }
}
