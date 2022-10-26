package com.minData.W2m.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minData.W2m.app.rest.SuperHeroController;
import com.minData.W2m.domain.model.SuperHero;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SuperHeroController.class)
@RunWith(SpringRunner.class)
public class SuperHeroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private StringBuilder URL = new StringBuilder().append("/superHeros");

    private static final Long id = 1L;

    private SuperHero superHero;

    @Before
    public void setUp() {
        superHero = new SuperHero();
    }

    @Test
    public void getSuperHerosTest() throws Exception {
        mockMvc.perform(get(URL.toString()))
                .andExpect(status().isOk());
    }

    @Test
    public void getSuperHeroByIdTest() throws Exception {
        mockMvc.perform(get(URL.append("/{id}").toString(), id))
                .andExpect(status().isOk());
    }

    @Test
    public void getSuperHeroByIdNotFoundTest() throws Exception {
        mockMvc.perform(get(URL.append("/{id}").toString(), id))
                .andExpect(status().isOk());
    }

    @Test
    public void getSuperHeroByNameTest() throws Exception {
        var name = "Superman";
        mockMvc.perform(get(URL.toString())
                        .param("name", name))
                .andExpect(status().isOk());
    }

    @Test
    public void getSuperHeroByNameNotFoundTest() throws Exception {
        var name = "Superman";
        mockMvc.perform(get(URL.toString())
                        .param("name", name))
                .andExpect(status().isOk());
    }

    @Test
    public void saveSuperHeroTest() throws Exception {
        mockMvc.perform(post(URL.toString())
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(superHero)))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateuperHeroTest() throws Exception {
        mockMvc.perform(put(URL.toString())
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(superHero)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateuperHeroNotFoundTest() throws Exception {
        mockMvc.perform(put(URL.toString())
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(superHero)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteSuperHeroTest() throws Exception {
        mockMvc.perform(delete(URL.append("/{id}").toString(), id))
                .andExpect(status().isOk());
    }
}
