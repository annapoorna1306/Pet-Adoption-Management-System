package com.adoption.repository;

import com.adoption.model.Veterinarian;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Veterinarian entity
 */
@Repository
public interface VeterinarianRepository extends MongoRepository<Veterinarian, String> {
}
