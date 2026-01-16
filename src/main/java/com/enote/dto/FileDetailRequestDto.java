package com.enote.dto;

import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileDetailRequestDto {
    
    @NotNull(message = "File is required")
    private MultipartFile file;
    
    private String displayFileName;  
    
    private String description; 
}