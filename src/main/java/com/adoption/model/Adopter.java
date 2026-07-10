package com.adoption.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Adopter entity representing a person who can adopt pets
 */
@Document(collection = "adopters")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Adopter {
    
    @Id
    private String id;
    
    private String name;
    
    private String email;
    
    private String phone;
    
    private String address;
}
