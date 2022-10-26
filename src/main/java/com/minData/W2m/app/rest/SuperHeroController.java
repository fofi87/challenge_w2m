package com.minData.W2m.app.rest;

import com.minData.W2m.domain.model.SuperHero;
import com.minData.W2m.domain.service.SuperHeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/superHeros")
public class SuperHeroController {

    @Autowired
    private SuperHeroService superHeroService;

    @GetMapping()
    public ResponseEntity<List<SuperHero>> getSuperHeros() {
        return new ResponseEntity<>(this.superHeroService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuperHero> getSuperHero(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.superHeroService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<SuperHero>> getSuperHeroByName(@RequestParam("name") String name) {
        return new ResponseEntity<>(this.superHeroService.findByName(name), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<SuperHero> save(@RequestBody SuperHero superHero) {
        return new ResponseEntity<>(this.superHeroService.save(superHero), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<SuperHero> update(@RequestBody SuperHero superHero) {
        return new ResponseEntity<>(this.superHeroService.update(superHero), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        this.superHeroService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
