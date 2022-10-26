package com.minData.W2m.services;

import com.minData.W2m.domain.exceptions.BadRequestException;
import com.minData.W2m.domain.exceptions.NotFoundException;
import com.minData.W2m.domain.model.SuperHero;
import com.minData.W2m.domain.repository.SuperHeroRepository;
import com.minData.W2m.domain.service.impl.SuperHeroServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class SuperHeroServiceTest {

    @Mock
    private SuperHeroRepository superHeroRepository;

    @InjectMocks
    private SuperHeroServiceImpl superHeroService;

    private SuperHero superHero;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        superHero = SuperHero.builder().name("SuperMan").age(35L).build();
    }

    @Test
    public void findAllTest() {
        given(superHeroRepository.findAll()).willReturn(List.of(superHero));

        List<SuperHero> response = superHeroService.findAll();

        assertNotNull(response);
        assertEquals(response.get(0).getName(), superHero.getName());
        assertEquals(response.get(0).getAge(), superHero.getAge());
        verify(this.superHeroRepository, times(1)).findAll();
    }

    @Test
    public void findByIdTest() {
        given(superHeroRepository.findById(anyLong())).willReturn(Optional.of(superHero));

        SuperHero response = superHeroService.findById(1L);

        assertNotNull(response);
        assertEquals(response.getName(), superHero.getName());
        assertEquals(response.getAge(), superHero.getAge());
        verify(this.superHeroRepository, times(1)).findById(1L);
    }

    @Test
    public void findByIdNotFoundTest() {
        given(superHeroRepository.findById(anyLong())).willReturn(Optional.empty());

        Exception response = assertThrows(NotFoundException.class, () -> {
            superHeroService.findById(1L);
        });

        assertTrue(response.getMessage().contains("SuperHero not found"));
        verify(this.superHeroRepository, times(1)).findById(1L);
    }

    @Test
    public void findByNameTest() {
        given(superHeroRepository.findByName(anyString())).willReturn(List.of(superHero));

        List<SuperHero> response = superHeroService.findByName("SuperMan");

        assertNotNull(response);
        assertEquals(response.get(0).getName(), superHero.getName());
        assertEquals(response.get(0).getAge(), superHero.getAge());
        verify(this.superHeroRepository, times(1)).findByName(anyString());
    }

    @Test
    public void saveTest() {
        given(superHeroRepository.save(superHero)).willReturn(superHero);

        SuperHero response = superHeroService.save(superHero);

        assertNotNull(response);
        assertEquals(response.getName(), superHero.getName());
        assertEquals(response.getAge(), superHero.getAge());
        verify(this.superHeroRepository, times(1)).save(superHero);
    }

    @Test
    public void saveBadRequestTest() {
        given(superHeroRepository.save(superHero)).willThrow(new BadRequestException("The origin is required"));

        Exception response = assertThrows(BadRequestException.class, () -> {
            superHeroService.save(superHero);
        });

        assertTrue(response.getMessage().contains("The origin is required"));
        verify(this.superHeroRepository, times(1)).save(superHero);
    }

    @Test
    public void updateTest() {
        superHero.setName("Iron Man");
        given(superHeroRepository.save(superHero)).willReturn(superHero);

        SuperHero response = superHeroService.update(superHero);

        assertNotNull(response);
        assertEquals(response.getName(), superHero.getName());
        assertEquals(response.getAge(), superHero.getAge());
        verify(this.superHeroRepository, times(1)).save(superHero);
    }

    @Test
    public void updateBadRequestTest() {
        superHero.setName("Iron Man");
        given(superHeroRepository.save(superHero)).willThrow(new BadRequestException("The origin is required"));

        Exception response = assertThrows(BadRequestException.class, () -> {
            superHeroService.update(superHero);
        });

        assertTrue(response.getMessage().contains("The origin is required"));
        verify(this.superHeroRepository, times(1)).save(superHero);
    }

    @Test
    public void deleteTest() {
        willDoNothing().given(superHeroRepository).delete(anyLong());

        superHeroService.delete(1L);

        verify(this.superHeroRepository, times(1)).delete(1L);
    }



}
