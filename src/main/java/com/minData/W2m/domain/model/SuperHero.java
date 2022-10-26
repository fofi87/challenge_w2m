package com.minData.W2m.domain.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Builder
public class SuperHero {

    @Id
    private Long id;
    private String name;
    private String origin;
    private Long age;
    private String superPower;

}
