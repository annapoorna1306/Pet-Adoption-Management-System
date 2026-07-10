package com.adoption.service;

import com.adoption.exception.ResourceNotFoundException;
import com.adoption.model.Pet;
import com.adoption.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for Pet entity with business logic
 */
@Service
@RequiredArgsConstructor
public class PetService {
    
    private final PetRepository petRepository;
    
    /**
     * Create/Add a new pet
     */
    public Pet addPet(Pet pet) {
        return petRepository.save(pet);
    }
    
    /**
     * Get all pets
     */
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }
    
    /**
     * Get pet by ID
     */
    public Pet getPetById(String id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found with id: " + id));
    }
    
    /**
     * Update pet
     */
    public Pet updatePet(String id, Pet petDetails) {
        Pet pet = getPetById(id);
        
        if (petDetails.getName() != null) {
            pet.setName(petDetails.getName());
        }
        if (petDetails.getSpecies() != null) {
            pet.setSpecies(petDetails.getSpecies());
        }
        if (petDetails.getBreed() != null) {
            pet.setBreed(petDetails.getBreed());
        }
        if (petDetails.getAge() > 0) {
            pet.setAge(petDetails.getAge());
        }
        if (petDetails.getHealthStatus() != null) {
            pet.setHealthStatus(petDetails.getHealthStatus());
        }
        if (petDetails.getShelterId() != null) {
            pet.setShelterId(petDetails.getShelterId());
        }
        if (petDetails.getAdoptionStatus() != null) {
            pet.setAdoptionStatus(petDetails.getAdoptionStatus());
        }
        
        return petRepository.save(pet);
    }
    
    /**
     * Delete pet
     */
    public void deletePet(String id) {
        if (!petRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pet not found with id: " + id);
        }
        petRepository.deleteById(id);
    }
    
    /**
     * Get pets by species
     */
    public List<Pet> getPetsBySpecies(String species) {
        return petRepository.findBySpecies(species);
    }
    
    /**
     * Get pets by adoption status
     */
    public List<Pet> getPetsByAdoptionStatus(String adoptionStatus) {
        return petRepository.findByAdoptionStatus(adoptionStatus);
    }
    
    /**
     * Get all pets in a specific shelter
     */
    public List<Pet> getPetsByShelterId(String shelterId) {
        return petRepository.findByShelterId(shelterId);
    }
}
