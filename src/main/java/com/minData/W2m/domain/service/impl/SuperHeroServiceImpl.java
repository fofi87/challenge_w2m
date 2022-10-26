package com.minData.W2m.domain.service.impl;

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
        return null;
    }

    @Override
    public SuperHero findById(Long id) {
        return null;
    }

    @Override
    public List<SuperHero> findByName(String name) {
        return null;
    }

    @Override
    public SuperHero save(SuperHero superHero) {
        return null;
    }

    @Override
    public SuperHero update(SuperHero superHero) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
