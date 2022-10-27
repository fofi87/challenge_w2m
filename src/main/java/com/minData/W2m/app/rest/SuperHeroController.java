package com.minData.W2m.app.rest;

import com.minData.W2m.app.api.SuperHeroApi;
import com.minData.W2m.aspects.LogAnnotation;
import com.minData.W2m.domain.service.SuperHeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/superHeros")
@LogAnnotation
public class SuperHeroController {

    @Autowired
    private SuperHeroService superHeroService;

    @GetMapping()
    public ResponseEntity<List<SuperHeroApi>> getSuperHeros() {
        return new ResponseEntity<>(this.superHeroService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuperHeroApi> getSuperHero(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.superHeroService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<List<SuperHeroApi>> getSuperHeroByName(@RequestParam("name") String name) {
        return new ResponseEntity<>(this.superHeroService.findByName(name), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<SuperHeroApi> save(@RequestBody SuperHeroApi superHeroApi) {
        return new ResponseEntity<>(this.superHeroService.save(superHeroApi), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<SuperHeroApi> update(@RequestBody SuperHeroApi superHeroApi) {
        return new ResponseEntity<>(this.superHeroService.update(superHeroApi), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        this.superHeroService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
