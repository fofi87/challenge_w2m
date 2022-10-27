package com.minData.W2m.domain.repository;

import com.minData.W2m.domain.model.SuperHero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuperHeroRepository extends JpaRepository<SuperHero, Long> {

    @Query("Select s From SuperHero s WHERE s.name LIKE CONCAT('%',:name,'%')")
    List<SuperHero> findByName(String name);

}
