package com.minData.W2m.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minData.W2m.app.rest.SuperHeroController;
import com.minData.W2m.domain.exceptions.BadRequestException;
import com.minData.W2m.domain.exceptions.NotFoundException;
import com.minData.W2m.domain.model.SuperHero;
import com.minData.W2m.domain.service.SuperHeroService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SuperHeroController.class)
@RunWith(SpringRunner.class)
public class SuperHeroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SuperHeroService superHeroService;

    private StringBuilder URL = new StringBuilder().append("/superHeros");

    private static final Long id = 1L;

    private SuperHero superHero;

    @Before
    public void setUp() {
        superHero = SuperHero.builder()
                .name("SuperMan")
                .age(35L)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @Test
    public void getSuperHerosTest() throws Exception {
        given(superHeroService.findAll()).willReturn(List.of(superHero));

        mockMvc.perform(get(URL.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value(superHero.getName()))
                .andExpect(jsonPath("$[0].age").value(superHero.getAge()));
    }

    @Test
    public void getSuperHeroByIdTest() throws Exception {
        given(superHeroService.findById(anyLong())).willReturn(superHero);

        mockMvc.perform(get(URL.append("/{id}").toString(), id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(superHero.getName()))
                .andExpect(jsonPath("$.age").value(superHero.getAge()));
    }

    @Test
    public void getSuperHeroByIdNotFoundTest() throws Exception {
        given(superHeroService.findById(anyLong())).willThrow(new NotFoundException("SuperHero not found"));

        mockMvc.perform(get(URL.append("/{id}").toString(), id))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getSuperHeroByNameTest() throws Exception {
        given(superHeroService.findByName(anyString())).willReturn(List.of(superHero));

        mockMvc.perform(get(URL.append("/name").toString())
                        .param("name", "SuperMan"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value(superHero.getName()))
                .andExpect(jsonPath("$[0].age").value(superHero.getAge()));
    }

    @Test
    public void saveSuperHeroTest() throws Exception {
        given(superHeroService.save(superHero)).willReturn(superHero);

        mockMvc.perform(post(URL.toString())
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(superHero)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(superHero.getName()))
                .andExpect(jsonPath("$.age").value(superHero.getAge()));
    }

    @Test
    public void saveSuperHeroBadRequestTest() throws Exception {
        given(superHeroService.save(superHero)).willThrow(new BadRequestException("The origin is required"));

        mockMvc.perform(post(URL.toString())
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(superHero)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateuperHeroTest() throws Exception {
        superHero.setName("Iron Man");
        given(superHeroService.update(any())).willReturn(superHero);

        mockMvc.perform(put(URL.toString())
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(superHero)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(superHero.getName()))
                .andExpect(jsonPath("$.age").value(superHero.getAge()));
    }

    @Test
    public void updateuperHeroNotFoundTest() throws Exception {
        given(superHeroService.update(any())).willThrow(new NotFoundException("SuperHero not found"));

        mockMvc.perform(put(URL.toString())
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(superHero)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteSuperHeroTest() throws Exception {
        willDoNothing().given(superHeroService).delete(anyLong());

        mockMvc.perform(delete(URL.append("/{id}").toString(), id))
                .andExpect(status().isOk());
    }
}
