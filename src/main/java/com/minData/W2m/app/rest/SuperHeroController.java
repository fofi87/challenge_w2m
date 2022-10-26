package com.minData.W2m.app.rest;

import com.minData.W2m.domain.model.SuperHero;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/superHeros")
public class SuperHeroController {

    @GetMapping()
    public ResponseEntity<List<SuperHero>> getSuperHeros() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<SuperHero>> getSuperHero(@PathVariable("id") Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<SuperHero>> getSuperHeroByName(@RequestParam("name") String name) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<SuperHero> save(@RequestBody SuperHero superHero) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<SuperHero> update(@RequestBody SuperHero superHero) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
