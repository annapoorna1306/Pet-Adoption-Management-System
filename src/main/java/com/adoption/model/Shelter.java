package com.adoption.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Shelter entity representing a pet shelter facility
 */
@Document(collection = "shelters")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shelter {
    
    @Id
    private String id;
    
    private String name;
    
    private String address;
    
    private String phone;
    
    private String email;
    
    private int capacity;
}
