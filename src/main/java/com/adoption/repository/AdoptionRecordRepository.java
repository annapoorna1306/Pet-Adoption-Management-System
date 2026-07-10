package com.adoption.repository;

import com.adoption.model.AdoptionRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for AdoptionRecord entity with custom query methods
 */
@Repository
public interface AdoptionRecordRepository extends MongoRepository<AdoptionRecord, String> {
    
    /**
     * Find adoption records by pet ID
     */
    List<AdoptionRecord> findByPetId(String petId);
    
    /**
     * Find adoption records by adopter ID
     */
    List<AdoptionRecord> findByAdopterId(String adopterId);
}
