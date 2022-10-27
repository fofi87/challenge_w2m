package com.minData.W2m.domain.service.impl;

import com.minData.W2m.domain.exceptions.NotFoundException;
import com.minData.W2m.domain.model.SuperHero;
import com.minData.W2m.domain.repository.SuperHeroRepository;
import com.minData.W2m.domain.service.SuperHeroService;
import com.minData.W2m.domain.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
@Service
public class SuperHeroServiceImpl implements SuperHeroService {

    @Autowired
    SuperHeroRepository superHeroRepository;

    @Autowired
    private Validator validator;

    @Override
    public List<SuperHero> findAll() {
        return this.superHeroRepository.findAll();
    }

    @Override
    @Cacheable(value= "superHeros", key="#id")
    public SuperHero findById(Long id) {
        return this.superHeroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("SuperHero not found"));
    }

    @Override
    @Cacheable(value= "superHeros", key="#name")
    public List<SuperHero> findByName(String name) {
        return this.superHeroRepository.findByName(name);
    }

    @Override
    @Transactional
    public SuperHero save(SuperHero superHero) {
        this.validator.validateSuperHero(superHero, Boolean.FALSE);
        superHero.setCreatedAt(LocalDateTime.now());
        superHero.setUpdatedAt(LocalDateTime.now());
        return this.superHeroRepository.save(superHero);
    }

    @Override
    @Transactional
    @CachePut(value="superHeros", key="#superHero.id")
    public SuperHero update(SuperHero superHero) {
        this.validator.validateSuperHero(superHero, Boolean.TRUE);
        this.superHeroRepository.findById(superHero.getId())
                .orElseThrow(() -> new NotFoundException("SuperHero not found"));
        superHero.setUpdatedAt(LocalDateTime.now());
        return this.superHeroRepository.save(superHero);
    }

    @Override
    @Transactional
    @CacheEvict("superHeros")
    public void delete(Long id) {
        this.superHeroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("SuperHero not found"));
        this.superHeroRepository.deleteById(id);
    }

}
