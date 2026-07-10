package com.adoption.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * Standard error response structure for API errors
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    
    private int status;
    
    private String message;
    
    private LocalDateTime timestamp;
    
    private String path;
}
