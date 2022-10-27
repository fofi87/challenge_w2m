package com.minData.W2m.app.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SuperHeroApi implements Serializable {

    private Long id;
    private String name;
    private String origin;
    private Long age;
    private String superPower;

}
