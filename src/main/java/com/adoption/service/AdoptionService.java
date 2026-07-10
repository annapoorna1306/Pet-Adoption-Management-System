package com.adoption.service;

import com.adoption.exception.BadRequestException;
import com.adoption.exception.ResourceNotFoundException;
import com.adoption.model.AdoptionRecord;
import com.adoption.model.Pet;
import com.adoption.repository.AdoptionRecordRepository;
import com.adoption.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Service class for AdoptionRecord entity with business logic
 */
@Service
@RequiredArgsConstructor
public class AdoptionService {
    
    private final AdoptionRecordRepository adoptionRecordRepository;
    private final PetRepository petRepository;
    private final PetService petService;
    private final AdopterService adopterService;
    
    /**
     * Create/Add a new adoption record
     */
    public AdoptionRecord addAdoptionRecord(AdoptionRecord adoptionRecord) {
        return adoptionRecordRepository.save(adoptionRecord);
    }
    
    /**
     * Get all adoption records
     */
    public List<AdoptionRecord> getAllAdoptionRecords() {
        return adoptionRecordRepository.findAll();
    }
    
    /**
     * Get adoption record by ID
     */
    public AdoptionRecord getAdoptionRecordById(String id) {
        return adoptionRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Adoption record not found with id: " + id));
    }
    
    /**
     * Update adoption record
     */
    public AdoptionRecord updateAdoptionRecord(String id, AdoptionRecord recordDetails) {
        AdoptionRecord record = getAdoptionRecordById(id);
        
        if (recordDetails.getPetId() != null) {
            record.setPetId(recordDetails.getPetId());
        }
        if (recordDetails.getAdopterId() != null) {
            record.setAdopterId(recordDetails.getAdopterId());
        }
        if (recordDetails.getAdoptionDate() != null) {
            record.setAdoptionDate(recordDetails.getAdoptionDate());
        }
        if (recordDetails.getStatus() != null) {
            record.setStatus(recordDetails.getStatus());
        }
        if (recordDetails.getNotes() != null) {
            record.setNotes(recordDetails.getNotes());
        }
        
        return adoptionRecordRepository.save(record);
    }
    
    /**
     * Delete adoption record
     */
    public void deleteAdoptionRecord(String id) {
        if (!adoptionRecordRepository.existsById(id)) {
            throw new ResourceNotFoundException("Adoption record not found with id: " + id);
        }
        adoptionRecordRepository.deleteById(id);
    }
    
    /**
     * Get adoption records by pet ID
     */
    public List<AdoptionRecord> getAdoptionsByPetId(String petId) {
        return adoptionRecordRepository.findByPetId(petId);
    }
    
    /**
     * Get adoption records by adopter ID
     */
    public List<AdoptionRecord> getAdoptionsByAdopterId(String adopterId) {
        return adoptionRecordRepository.findByAdopterId(adopterId);
    }
    
    /**
     * Process a complete adoption:
     * - Validates that pet and adopter exist
     * - Updates pet status to "adopted"
     * - Creates an adoption record
     */
    public AdoptionRecord processAdoption(String petId, String adopterId) {
        // Verify pet exists and is available
        Pet pet = petService.getPetById(petId);
        if (!"available".equalsIgnoreCase(pet.getAdoptionStatus())) {
            throw new BadRequestException("Pet is not available for adoption");
        }
        
        // Verify adopter exists
        adopterService.getAdopterById(adopterId);
        
        // Update pet status to "adopted"
        pet.setAdoptionStatus("adopted");
        petRepository.save(pet);
        
        // Create adoption record
        AdoptionRecord adoptionRecord = new AdoptionRecord();
        adoptionRecord.setPetId(petId);
        adoptionRecord.setAdopterId(adopterId);
        adoptionRecord.setAdoptionDate(new Date());
        adoptionRecord.setStatus("completed");
        
        return adoptionRecordRepository.save(adoptionRecord);
    }
}
