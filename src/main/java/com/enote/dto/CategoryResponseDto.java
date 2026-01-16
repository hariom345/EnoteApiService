package com.enote.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDto extends BaseAuditDto{
	private Integer id;
	private String name;
	private String description;
	private Boolean isDeleted;
//	private Short status;
//	private Integer createdBy;
//	private LocalDateTime createdOn;
//	private Integer updatedBy;
//	private LocalDateTime updatedOn;
//	private Integer deletedBy;
//	private LocalDateTime deletedOn;

}
