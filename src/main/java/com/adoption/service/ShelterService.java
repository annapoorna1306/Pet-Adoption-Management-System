package com.adoption.service;

import com.adoption.exception.ResourceNotFoundException;
import com.adoption.model.Shelter;
import com.adoption.repository.ShelterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for Shelter entity with business logic
 */
@Service
@RequiredArgsConstructor
public class ShelterService {
    
    private final ShelterRepository shelterRepository;
    private final PetService petService;
    
    /**
     * Create/Add a new shelter
     */
    public Shelter addShelter(Shelter shelter) {
        return shelterRepository.save(shelter);
    }
    
    /**
     * Get all shelters
     */
    public List<Shelter> getAllShelters() {
        return shelterRepository.findAll();
    }
    
    /**
     * Get shelter by ID
     */
    public Shelter getShelterById(String id) {
        return shelterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shelter not found with id: " + id));
    }
    
    /**
     * Update shelter
     */
    public Shelter updateShelter(String id, Shelter shelterDetails) {
        Shelter shelter = getShelterById(id);
        
        if (shelterDetails.getName() != null) {
            shelter.setName(shelterDetails.getName());
        }
        if (shelterDetails.getAddress() != null) {
            shelter.setAddress(shelterDetails.getAddress());
        }
        if (shelterDetails.getPhone() != null) {
            shelter.setPhone(shelterDetails.getPhone());
        }
        if (shelterDetails.getEmail() != null) {
            shelter.setEmail(shelterDetails.getEmail());
        }
        if (shelterDetails.getCapacity() > 0) {
            shelter.setCapacity(shelterDetails.getCapacity());
        }
        
        return shelterRepository.save(shelter);
    }
    
    /**
     * Delete shelter
     */
    public void deleteShelter(String id) {
        if (!shelterRepository.existsById(id)) {
            throw new ResourceNotFoundException("Shelter not found with id: " + id);
        }
        shelterRepository.deleteById(id);
    }
    
    /**
     * Get all pets in a specific shelter
     */
    public Object getPetsByShelter(String shelterId) {
        // First verify shelter exists
        getShelterById(shelterId);
        
        // Return pets for this shelter
        return petService.getPetsByShelterId(shelterId);
    }
}
