package com.enote.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDto {
	private Integer id;
	@NotBlank(message = "Category name cannot be empty")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
	private String name;
	private String description;
	private Short status;
	private Boolean isDeleted;
	private Integer createdBy;
	private LocalDateTime createdOn;
	private Integer updatedBy;
	private LocalDateTime updatedOn;
	private Integer deletedBy;
	private LocalDateTime deletedOn;

}
