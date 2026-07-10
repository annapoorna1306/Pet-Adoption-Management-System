package com.adoption.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Pet entity representing a pet available for adoption
 */
@Document(collection = "pets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
    
    @Id
    private String id;
    
    private String name;
    
    private String species;
    
    private String breed;
    
    private int age;
    
    private String healthStatus;
    
    private String shelterId;
    
    private String adoptionStatus; // "available" or "adopted"
}
