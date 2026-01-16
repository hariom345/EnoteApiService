package com.enote.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileDetailResponseDto extends BaseAuditDto{
	    private Integer id;
	    private String uploadFileName;
	    private String displayFileName;
	    private String originalFileName;
	    private String path;
	    private Integer fileSize;
}
