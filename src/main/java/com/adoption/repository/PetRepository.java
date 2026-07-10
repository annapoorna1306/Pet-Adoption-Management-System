package com.adoption.repository;

import com.adoption.model.Pet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Pet entity with custom query methods
 */
@Repository
public interface PetRepository extends MongoRepository<Pet, String> {
    
    /**
     * Find pets by species
     */
    List<Pet> findBySpecies(String species);
    
    /**
     * Find pets by adoption status
     */
    List<Pet> findByAdoptionStatus(String adoptionStatus);
    
    /**
     * Find all pets in a specific shelter
     */
    List<Pet> findByShelterId(String shelterId);
}
