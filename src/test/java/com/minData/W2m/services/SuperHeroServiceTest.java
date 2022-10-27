package com.minData.W2m.services;

import com.minData.W2m.app.api.SuperHeroApi;
import com.minData.W2m.domain.exceptions.BadRequestException;
import com.minData.W2m.domain.exceptions.NotFoundException;
import com.minData.W2m.domain.model.SuperHero;
import com.minData.W2m.domain.repository.SuperHeroRepository;
import com.minData.W2m.domain.service.impl.SuperHeroServiceImpl;
import com.minData.W2m.domain.validators.Validator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class SuperHeroServiceTest {

    @Mock
    private SuperHeroRepository superHeroRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private SuperHeroServiceImpl superHeroService;

    @Mock
    private Validator validator;

    private SuperHero superHero;

    private SuperHeroApi superHeroApi;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        superHero = SuperHero.builder()
                .name("SuperMan")
                .age(35L)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        superHeroApi = SuperHeroApi.builder()
                .name("SuperMan")
                .age(35L)
                .build();
    }

    @Test
    public void findAllTest() {
        given(superHeroRepository.findAll()).willReturn(List.of(superHero));
        given(modelMapper.map(superHero, SuperHeroApi.class)).willReturn(superHeroApi);
        List<SuperHeroApi> response = superHeroService.findAll();

        assertNotNull(response);
        assertEquals(response.get(0).getName(), superHero.getName());
        assertEquals(response.get(0).getAge(), superHero.getAge());
        verify(this.superHeroRepository, times(1)).findAll();
    }

    @Test
    public void findByIdTest() {
        given(superHeroRepository.findById(anyLong())).willReturn(Optional.of(superHero));
        given(modelMapper.map(superHero, SuperHeroApi.class)).willReturn(superHeroApi);

        SuperHeroApi response = superHeroService.findById(1L);

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
        given(modelMapper.map(superHero, SuperHeroApi.class)).willReturn(superHeroApi);

        List<SuperHeroApi> response = superHeroService.findByName("SuperMan");

        assertNotNull(response);
        assertEquals(response.get(0).getName(), superHero.getName());
        assertEquals(response.get(0).getAge(), superHero.getAge());
        verify(this.superHeroRepository, times(1)).findByName(anyString());
    }

    @Test
    public void saveTest() {
        given(superHeroRepository.save(superHero)).willReturn(superHero);
        doNothing().when(this.validator).validateSuperHero(superHeroApi, Boolean.FALSE);
        given(modelMapper.map(superHeroApi, SuperHero.class)).willReturn(superHero);
        given(modelMapper.map(superHero, SuperHeroApi.class)).willReturn(superHeroApi);

        SuperHeroApi response = superHeroService.save(superHeroApi);

        assertNotNull(response);
        assertEquals(response.getName(), superHeroApi.getName());
        assertEquals(response.getAge(), superHeroApi.getAge());
        verify(this.superHeroRepository, times(1)).save(superHero);
    }

    @Test
    public void saveBadRequestTest() {
        doThrow(new BadRequestException("The origin is required")).when(this.validator).validateSuperHero(superHeroApi, Boolean.FALSE);

        Exception response = assertThrows(BadRequestException.class, () -> {
            superHeroService.save(superHeroApi);
        });

        assertTrue(response.getMessage().contains("The origin is required"));
        verify(this.superHeroRepository, times(0)).save(superHero);
    }

    @Test
    public void updateTest() {
        superHero.setName("Iron Man");
        doNothing().when(this.validator).validateSuperHero(superHeroApi, Boolean.TRUE);
        given(superHeroRepository.findById(superHero.getId())).willReturn(Optional.of(superHero));
        given(superHeroRepository.save(superHero)).willReturn(superHero);
        given(modelMapper.map(superHeroApi, SuperHero.class)).willReturn(superHero);
        given(modelMapper.map(superHero, SuperHeroApi.class)).willReturn(superHeroApi);

        SuperHeroApi response = superHeroService.update(superHeroApi);

        assertNotNull(response);
        assertEquals(response.getName(), superHeroApi.getName());
        assertEquals(response.getAge(), superHeroApi.getAge());
        verify(this.superHeroRepository, times(1)).save(superHero);
    }

    @Test
    public void updateBadRequestTest() {
        superHero.setName("Iron Man");
        doThrow(new BadRequestException("The origin is required")).when(this.validator).validateSuperHero(superHeroApi, Boolean.TRUE);

        Exception response = assertThrows(BadRequestException.class, () -> {
            superHeroService.update(superHeroApi);
        });

        assertTrue(response.getMessage().contains("The origin is required"));
        verify(this.superHeroRepository, times(0)).save(superHero);
    }

    @Test
    public void deleteTest() {
        given(superHeroRepository.findById(anyLong())).willReturn(Optional.of(superHero));
        willDoNothing().given(superHeroRepository).deleteById(anyLong());

        superHeroService.delete(1L);

        verify(this.superHeroRepository, times(1)).deleteById(1L);
    }

}
