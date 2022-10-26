package com.minData.W2m.domain.service;

import com.minData.W2m.domain.model.SuperHero;

import java.util.List;

public interface SuperHeroService {

    List<SuperHero> findAll();
    SuperHero findById(Long id);
    List<SuperHero> findByName(String name);
    SuperHero save(SuperHero superHero);
    SuperHero update(SuperHero superHero);
    void delete(Long id);


}
