package com.adoption.repository;

import com.adoption.model.Shelter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Shelter entity
 */
@Repository
public interface ShelterRepository extends MongoRepository<Shelter, String> {
}
