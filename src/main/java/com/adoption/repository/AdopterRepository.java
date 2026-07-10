package com.adoption.repository;

import com.adoption.model.Adopter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Adopter entity
 */
@Repository
public interface AdopterRepository extends MongoRepository<Adopter, String> {
}
