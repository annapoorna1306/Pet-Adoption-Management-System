package com.adoption.controller;

import com.adoption.model.AdoptionRecord;
import com.adoption.service.AdoptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST Controller for AdoptionRecord entity
 * Base URL: /api/adoptions
 */
@RestController
@RequestMapping("/api/adoptions")
@RequiredArgsConstructor
public class AdoptionRecordController {
    
    private final AdoptionService adoptionService;
    
    /**
     * POST / - Create adoption record
     */
    @PostMapping
    public ResponseEntity<AdoptionRecord> addAdoptionRecord(@RequestBody AdoptionRecord adoptionRecord) {
        AdoptionRecord savedRecord = adoptionService.addAdoptionRecord(adoptionRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRecord);
    }
    
    /**
     * POST /process - Process complete adoption
     * Request body should contain: { "petId": "...", "adopterId": "..." }
     */
    @PostMapping("/process")
    public ResponseEntity<AdoptionRecord> processAdoption(@RequestBody Map<String, String> request) {
        String petId = request.get("petId");
        String adopterId = request.get("adopterId");
        
        AdoptionRecord adoptionRecord = adoptionService.processAdoption(petId, adopterId);
        return ResponseEntity.status(HttpStatus.CREATED).body(adoptionRecord);
    }
    
    /**
     * GET / - Get all adoption records
     */
    @GetMapping
    public ResponseEntity<List<AdoptionRecord>> getAllAdoptionRecords() {
        List<AdoptionRecord> records = adoptionService.getAllAdoptionRecords();
        return ResponseEntity.ok(records);
    }
    
    /**
     * GET /{id} - Get adoption record by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<AdoptionRecord> getAdoptionRecordById(@PathVariable String id) {
        AdoptionRecord record = adoptionService.getAdoptionRecordById(id);
        return ResponseEntity.ok(record);
    }
    
    /**
     * GET /pet/{petId} - Get adoptions by pet ID
     */
    @GetMapping("/pet/{petId}")
    public ResponseEntity<List<AdoptionRecord>> getAdoptionsByPetId(@PathVariable String petId) {
        List<AdoptionRecord> records = adoptionService.getAdoptionsByPetId(petId);
        return ResponseEntity.ok(records);
    }
    
    /**
     * GET /adopter/{adopterId} - Get adoptions by adopter ID
     */
    @GetMapping("/adopter/{adopterId}")
    public ResponseEntity<List<AdoptionRecord>> getAdoptionsByAdopterId(@PathVariable String adopterId) {
        List<AdoptionRecord> records = adoptionService.getAdoptionsByAdopterId(adopterId);
        return ResponseEntity.ok(records);
    }
    
    /**
     * PUT /{id} - Update adoption record
     */
    @PutMapping("/{id}")
    public ResponseEntity<AdoptionRecord> updateAdoptionRecord(@PathVariable String id, @RequestBody AdoptionRecord recordDetails) {
        AdoptionRecord updatedRecord = adoptionService.updateAdoptionRecord(id, recordDetails);
        return ResponseEntity.ok(updatedRecord);
    }
    
    /**
     * DELETE /{id} - Delete adoption record
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdoptionRecord(@PathVariable String id) {
        adoptionService.deleteAdoptionRecord(id);
        return ResponseEntity.ok("Adoption record deleted successfully");
    }
}
