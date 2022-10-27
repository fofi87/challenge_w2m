package com.minData.W2m.domain.service.impl;

import com.minData.W2m.app.api.SuperHeroApi;
import com.minData.W2m.domain.exceptions.NotFoundException;
import com.minData.W2m.domain.model.SuperHero;
import com.minData.W2m.domain.repository.SuperHeroRepository;
import com.minData.W2m.domain.service.SuperHeroService;
import com.minData.W2m.domain.validators.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuperHeroServiceImpl implements SuperHeroService {

    @Autowired
    private SuperHeroRepository superHeroRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Validator validator;

    @Override
    public List<SuperHeroApi> findAll() {
        return this.map(this.superHeroRepository.findAll(), SuperHeroApi.class);
    }

    @Override
    @Cacheable(value= "superHeros", key="#id")
    public SuperHeroApi findById(Long id) {
        return this.modelMapper.map(this.superHeroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("SuperHero not found")), SuperHeroApi.class);
    }

    @Override
    @Cacheable(value= "superHeros", key="#name")
    public List<SuperHeroApi> findByName(String name) {
        return this.map(this.superHeroRepository.findByName(name), SuperHeroApi.class);
    }

    @Override
    @Transactional
    public SuperHeroApi save(SuperHeroApi superHeroApi) {
        this.validator.validateSuperHero(superHeroApi, Boolean.FALSE);
        SuperHero entity = this.modelMapper.map(superHeroApi, SuperHero.class);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return this.modelMapper.map(this.superHeroRepository.save(entity), SuperHeroApi.class);
    }

    @Override
    @Transactional
    @CachePut(value="superHeros", key="#superHeroApi.id")
    public SuperHeroApi update(SuperHeroApi superHeroApi) {
        this.validator.validateSuperHero(superHeroApi, Boolean.TRUE);
        this.superHeroRepository.findById(superHeroApi.getId())
                .orElseThrow(() -> new NotFoundException("SuperHero not found"));
        SuperHero entity = this.modelMapper.map(superHeroApi, SuperHero.class);
        entity.setUpdatedAt(LocalDateTime.now());
        return this.modelMapper.map(this.superHeroRepository.save(entity), SuperHeroApi.class);
    }

    @Override
    @Transactional
    @CacheEvict("superHeros")
    public void delete(Long id) {
        this.superHeroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("SuperHero not found"));
        this.superHeroRepository.deleteById(id);
    }

    private List<SuperHeroApi> map(List<SuperHero> list, Class<SuperHeroApi> apiClass) {
        return  list.stream()
                .map(entity -> modelMapper.map(entity, apiClass))
                .collect(Collectors.toList());
    }

}
