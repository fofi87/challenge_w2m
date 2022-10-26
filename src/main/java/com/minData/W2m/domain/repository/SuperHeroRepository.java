package com.minData.W2m.domain.repository;

import com.minData.W2m.domain.model.SuperHero;

import java.util.List;
import java.util.Optional;

public interface SuperHeroRepository {

    List<SuperHero> findAll();

    Optional<SuperHero> findById(Long id);

    List<SuperHero> findByName(String name);

    SuperHero save(SuperHero superHero);

    void delete(long id);
}
