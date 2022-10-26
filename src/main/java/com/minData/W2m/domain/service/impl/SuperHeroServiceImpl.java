package com.minData.W2m.domain.service.impl;

import com.minData.W2m.domain.exceptions.NotFoundException;
import com.minData.W2m.domain.model.SuperHero;
import com.minData.W2m.domain.repository.SuperHeroRepository;
import com.minData.W2m.domain.service.SuperHeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SuperHeroServiceImpl implements SuperHeroService {

    @Autowired
    SuperHeroRepository superHeroRepository;

    @Override
    public List<SuperHero> findAll() {
        return this.superHeroRepository.findAll();
    }

    @Override
    public SuperHero findById(Long id) {
        return this.superHeroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("SuperHero not found"));
    }

    @Override
    public List<SuperHero> findByName(String name) {
        return this.superHeroRepository.findByName(name);
    }

    @Override
    public SuperHero save(SuperHero superHero) {
        return this.superHeroRepository.save(superHero);
    }

    @Override
    public SuperHero update(SuperHero superHero) {
        return this.superHeroRepository.save(superHero);
    }

    @Override
    public void delete(Long id) {
        this.superHeroRepository.delete(id);
    }
}
