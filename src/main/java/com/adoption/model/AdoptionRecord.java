package com.adoption.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * AdoptionRecord entity representing a pet adoption transaction
 */
@Document(collection = "adoptionRecords")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdoptionRecord {
    
    @Id
    private String id;
    
    private String petId;
    
    private String adopterId;
    
    private Date adoptionDate;
    
    private String status; // "pending", "completed", "cancelled"
    
    private String notes;
}
