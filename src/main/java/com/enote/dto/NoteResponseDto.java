package com.enote.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoteResponseDto extends BaseAuditDto{
	
	private Integer id;
	private String title;
	private String description;
	private CategorySummary CategorySummary; 
    private FileSummary fileSummary;
//	private Short status;
//	private Integer createdBy;
//	private LocalDateTime createdOn;
//	private Integer updatedBy;
//	private LocalDateTime updatedOn;
//	private Integer deletedBy;
//	private LocalDateTime deletedOn;
	
	@Getter @Setter
    public static class CategorySummary {
        private Integer id;
        private String name;
        private String description;
    }

    @Getter @Setter
    public static class FileSummary {
        private Integer id;
        private String displayFileName;
        private String path;
        private Long fileSize;
    }
	
}