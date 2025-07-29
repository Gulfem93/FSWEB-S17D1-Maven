package com.workintech.fswebs17d1.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workintech.fswebs17d1.entity.Animal;

import jakarta.annotation.PostConstruct;


@RestController
@RequestMapping(path = "/workintech/animal")
public class AnimalController {
    private Map<Integer, Animal> animals;

    @PostConstruct
    public void loadAll(){
        this.animals=new HashMap<>();
        this.animals.put(1, new Animal(1, "maymun"));
    }

    @PostMapping
    public Animal addAnimal(@RequestBody Animal animal){
        this.animals.put(animal.getId(), animal);
        return animal;
    }

    @GetMapping
    public List<Animal> getAnimals(){
        return new ArrayList<>(animals.values());
    }

    @GetMapping("/{id}")
    public Animal getAnimal(@PathVariable int id){
        if(id<0){
            return null;
        }
        return this.animals.get(id);
    }

    @PutMapping("/{id}")
    public Animal updateAnimal(@PathVariable int id, @RequestBody Animal newAnimal){
        animals.replace(id, newAnimal);
        return this.animals.get(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable int id){
        this.animals.remove(id);

    }

}