package com.minData.W2m.domain.validators;

import com.minData.W2m.app.api.SuperHeroApi;
import com.minData.W2m.domain.exceptions.BadRequestException;
import org.springframework.stereotype.Component;

@Component
public class Validator {

    public void validateSuperHero(SuperHeroApi superHero, Boolean withId) {
        if(withId && superHero.getId() == null) {
            throw new BadRequestException("The id is required");
        }
        if (superHero.getName() == null || superHero.getName().isBlank()) {
            throw new BadRequestException("The name is required");
        }
        if(superHero.getAge() == null) {
            throw new BadRequestException("The age is required");
        }
    }
}
