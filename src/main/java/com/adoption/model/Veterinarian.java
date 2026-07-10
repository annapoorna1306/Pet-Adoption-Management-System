package com.adoption.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Veterinarian entity representing a veterinary professional
 */
@Document(collection = "veterinarians")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Veterinarian {
    
    @Id
    private String id;
    
    private String name;
    
    private String specialization;
    
    private String clinicAddress;
    
    private String phone;
}
