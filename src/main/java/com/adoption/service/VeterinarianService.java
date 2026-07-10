package com.adoption.service;

import com.adoption.exception.ResourceNotFoundException;
import com.adoption.model.Veterinarian;
import com.adoption.repository.VeterinarianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for Veterinarian entity with business logic
 */
@Service
@RequiredArgsConstructor
public class VeterinarianService {
    
    private final VeterinarianRepository veterinarianRepository;
    
    /**
     * Create/Add a new veterinarian
     */
    public Veterinarian addVeterinarian(Veterinarian veterinarian) {
        return veterinarianRepository.save(veterinarian);
    }
    
    /**
     * Get all veterinarians
     */
    public List<Veterinarian> getAllVeterinarians() {
        return veterinarianRepository.findAll();
    }
    
    /**
     * Get veterinarian by ID
     */
    public Veterinarian getVeterinarianById(String id) {
        return veterinarianRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Veterinarian not found with id: " + id));
    }
    
    /**
     * Update veterinarian
     */
    public Veterinarian updateVeterinarian(String id, Veterinarian veterinarianDetails) {
        Veterinarian veterinarian = getVeterinarianById(id);
        
        if (veterinarianDetails.getName() != null) {
            veterinarian.setName(veterinarianDetails.getName());
        }
        if (veterinarianDetails.getSpecialization() != null) {
            veterinarian.setSpecialization(veterinarianDetails.getSpecialization());
        }
        if (veterinarianDetails.getClinicAddress() != null) {
            veterinarian.setClinicAddress(veterinarianDetails.getClinicAddress());
        }
        if (veterinarianDetails.getPhone() != null) {
            veterinarian.setPhone(veterinarianDetails.getPhone());
        }
        
        return veterinarianRepository.save(veterinarian);
    }
    
    /**
     * Delete veterinarian
     */
    public void deleteVeterinarian(String id) {
        if (!veterinarianRepository.existsById(id)) {
            throw new ResourceNotFoundException("Veterinarian not found with id: " + id);
        }
        veterinarianRepository.deleteById(id);
    }
}
