package com.adoption.service;

import com.adoption.exception.ResourceNotFoundException;
import com.adoption.model.Adopter;
import com.adoption.repository.AdopterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for Adopter entity with business logic
 */
@Service
@RequiredArgsConstructor
public class AdopterService {
    
    private final AdopterRepository adopterRepository;
    
    /**
     * Create/Add a new adopter
     */
    public Adopter addAdopter(Adopter adopter) {
        return adopterRepository.save(adopter);
    }
    
    /**
     * Get all adopters
     */
    public List<Adopter> getAllAdopters() {
        return adopterRepository.findAll();
    }
    
    /**
     * Get adopter by ID
     */
    public Adopter getAdopterById(String id) {
        return adopterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Adopter not found with id: " + id));
    }
    
    /**
     * Update adopter
     */
    public Adopter updateAdopter(String id, Adopter adopterDetails) {
        Adopter adopter = getAdopterById(id);
        
        if (adopterDetails.getName() != null) {
            adopter.setName(adopterDetails.getName());
        }
        if (adopterDetails.getEmail() != null) {
            adopter.setEmail(adopterDetails.getEmail());
        }
        if (adopterDetails.getPhone() != null) {
            adopter.setPhone(adopterDetails.getPhone());
        }
        if (adopterDetails.getAddress() != null) {
            adopter.setAddress(adopterDetails.getAddress());
        }
        
        return adopterRepository.save(adopter);
    }
    
    /**
     * Delete adopter
     */
    public void deleteAdopter(String id) {
        if (!adopterRepository.existsById(id)) {
            throw new ResourceNotFoundException("Adopter not found with id: " + id);
        }
        adopterRepository.deleteById(id);
    }
}
