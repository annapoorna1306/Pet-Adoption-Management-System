package com.adoption.controller;

import com.adoption.model.Pet;
import com.adoption.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Pet entity
 * Base URL: /api/pets
 */
@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor
public class PetController {
    
    private final PetService petService;
    
    /**
     * POST / - Add a new pet
     */
    @PostMapping
    public ResponseEntity<Pet> addPet(@RequestBody Pet pet) {
        Pet savedPet = petService.addPet(pet);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPet);
    }
    
    /**
     * GET / - Get all pets
     */
    @GetMapping
    public ResponseEntity<List<Pet>> getAllPets() {
        List<Pet> pets = petService.getAllPets();
        return ResponseEntity.ok(pets);
    }
    
    /**
     * GET /{id} - Get pet by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable String id) {
        Pet pet = petService.getPetById(id);
        return ResponseEntity.ok(pet);
    }
    
    /**
     * GET /species/{species} - Get pets by species
     */
    @GetMapping("/species/{species}")
    public ResponseEntity<List<Pet>> getPetsBySpecies(@PathVariable String species) {
        List<Pet> pets = petService.getPetsBySpecies(species);
        return ResponseEntity.ok(pets);
    }
    
    /**
     * GET /status/{status} - Get pets by adoption status
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Pet>> getPetsByAdoptionStatus(@PathVariable String status) {
        List<Pet> pets = petService.getPetsByAdoptionStatus(status);
        return ResponseEntity.ok(pets);
    }
    
    /**
     * PUT /{id} - Update pet
     */
    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable String id, @RequestBody Pet petDetails) {
        Pet updatedPet = petService.updatePet(id, petDetails);
        return ResponseEntity.ok(updatedPet);
    }
    
    /**
     * DELETE /{id} - Delete pet
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePet(@PathVariable String id) {
        petService.deletePet(id);
        return ResponseEntity.ok("Pet deleted successfully");
    }
}
