package com.adoption.controller;

import com.adoption.model.Adopter;
import com.adoption.service.AdopterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Adopter entity
 * Base URL: /api/adopters
 */
@RestController
@RequestMapping("/api/adopters")
@RequiredArgsConstructor
public class AdopterController {
    
    private final AdopterService adopterService;
    
    /**
     * POST / - Add a new adopter
     */
    @PostMapping
    public ResponseEntity<Adopter> addAdopter(@RequestBody Adopter adopter) {
        Adopter savedAdopter = adopterService.addAdopter(adopter);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAdopter);
    }
    
    /**
     * GET / - Get all adopters
     */
    @GetMapping
    public ResponseEntity<List<Adopter>> getAllAdopters() {
        List<Adopter> adopters = adopterService.getAllAdopters();
        return ResponseEntity.ok(adopters);
    }
    
    /**
     * GET /{id} - Get adopter by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Adopter> getAdopterById(@PathVariable String id) {
        Adopter adopter = adopterService.getAdopterById(id);
        return ResponseEntity.ok(adopter);
    }
    
    /**
     * PUT /{id} - Update adopter
     */
    @PutMapping("/{id}")
    public ResponseEntity<Adopter> updateAdopter(@PathVariable String id, @RequestBody Adopter adopterDetails) {
        Adopter updatedAdopter = adopterService.updateAdopter(id, adopterDetails);
        return ResponseEntity.ok(updatedAdopter);
    }
    
    /**
     * DELETE /{id} - Delete adopter
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdopter(@PathVariable String id) {
        adopterService.deleteAdopter(id);
        return ResponseEntity.ok("Adopter deleted successfully");
    }
}
