package com.adoption.controller;

import com.adoption.model.Veterinarian;
import com.adoption.service.VeterinarianService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Veterinarian entity
 * Base URL: /api/veterinarians
 */
@RestController
@RequestMapping("/api/veterinarians")
@RequiredArgsConstructor
public class VeterinarianController {
    
    private final VeterinarianService veterinarianService;
    
    /**
     * POST / - Add a new veterinarian
     */
    @PostMapping
    public ResponseEntity<Veterinarian> addVeterinarian(@RequestBody Veterinarian veterinarian) {
        Veterinarian savedVeterinarian = veterinarianService.addVeterinarian(veterinarian);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVeterinarian);
    }
    
    /**
     * GET / - Get all veterinarians
     */
    @GetMapping
    public ResponseEntity<List<Veterinarian>> getAllVeterinarians() {
        List<Veterinarian> veterinarians = veterinarianService.getAllVeterinarians();
        return ResponseEntity.ok(veterinarians);
    }
    
    /**
     * GET /{id} - Get veterinarian by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Veterinarian> getVeterinarianById(@PathVariable String id) {
        Veterinarian veterinarian = veterinarianService.getVeterinarianById(id);
        return ResponseEntity.ok(veterinarian);
    }
    
    /**
     * PUT /{id} - Update veterinarian
     */
    @PutMapping("/{id}")
    public ResponseEntity<Veterinarian> updateVeterinarian(@PathVariable String id, @RequestBody Veterinarian veterinarianDetails) {
        Veterinarian updatedVeterinarian = veterinarianService.updateVeterinarian(id, veterinarianDetails);
        return ResponseEntity.ok(updatedVeterinarian);
    }
    
    /**
     * DELETE /{id} - Delete veterinarian
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVeterinarian(@PathVariable String id) {
        veterinarianService.deleteVeterinarian(id);
        return ResponseEntity.ok("Veterinarian deleted successfully");
    }
}
