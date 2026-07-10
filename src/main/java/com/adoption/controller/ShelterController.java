package com.adoption.controller;

import com.adoption.model.Pet;
import com.adoption.model.Shelter;
import com.adoption.service.ShelterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Shelter entity
 * Base URL: /api/shelters
 */
@RestController
@RequestMapping("/api/shelters")
@RequiredArgsConstructor
public class ShelterController {
    
    private final ShelterService shelterService;
    
    /**
     * POST / - Add a new shelter
     */
    @PostMapping
    public ResponseEntity<Shelter> addShelter(@RequestBody Shelter shelter) {
        Shelter savedShelter = shelterService.addShelter(shelter);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedShelter);
    }
    
    /**
     * GET / - Get all shelters
     */
    @GetMapping
    public ResponseEntity<List<Shelter>> getAllShelters() {
        List<Shelter> shelters = shelterService.getAllShelters();
        return ResponseEntity.ok(shelters);
    }
    
    /**
     * GET /{id} - Get shelter by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Shelter> getShelterById(@PathVariable String id) {
        Shelter shelter = shelterService.getShelterById(id);
        return ResponseEntity.ok(shelter);
    }
    
    /**
     * GET /{id}/pets - Get all pets in a specific shelter
     */
    @GetMapping("/{id}/pets")
    public ResponseEntity<?> getPetsByShelter(@PathVariable String id) {
        Object pets = shelterService.getPetsByShelter(id);
        return ResponseEntity.ok(pets);
    }
    
    /**
     * PUT /{id} - Update shelter
     */
    @PutMapping("/{id}")
    public ResponseEntity<Shelter> updateShelter(@PathVariable String id, @RequestBody Shelter shelterDetails) {
        Shelter updatedShelter = shelterService.updateShelter(id, shelterDetails);
        return ResponseEntity.ok(updatedShelter);
    }
    
    /**
     * DELETE /{id} - Delete shelter
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShelter(@PathVariable String id) {
        shelterService.deleteShelter(id);
        return ResponseEntity.ok("Shelter deleted successfully");
    }
}
