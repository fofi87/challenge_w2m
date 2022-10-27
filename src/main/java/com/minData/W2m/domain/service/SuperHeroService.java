package com.minData.W2m.domain.service;

import com.minData.W2m.app.api.SuperHeroApi;
import java.util.List;

public interface SuperHeroService {

    List<SuperHeroApi> findAll();
    SuperHeroApi findById(Long id);
    List<SuperHeroApi> findByName(String name);
    SuperHeroApi save(SuperHeroApi superHeroApi);
    SuperHeroApi update(SuperHeroApi superHeroApi);
    void delete(Long id);


}
