package com.enote.model;
import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "notes")
public class NoteEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 1000, nullable = false)
	private String title;
	
	@Column(length = 1000)
	private String description;
	
	@Column(name = "category_id")
	private Integer categoryId;
	
	@Column(name = "file_id")
	private Integer fileId;
	
	@Column(columnDefinition = "smallint default 1")
	private Short status = 1;
	
	@CreatedBy
	@Column(name = "created_by", updatable = false)
	private Integer createdBy;
	
	@CreatedDate
	@Column(name = "created_on", updatable = false)
	private LocalDateTime createdOn;
	
	@LastModifiedBy
	@Column(name = "updated_by", insertable = false)
	private Integer updatedBy;
	
	@LastModifiedDate
	@Column(name = "updated_on", insertable = false)
	private LocalDateTime updatedOn;
	
	@Column(name = "deleted_by")
	private Integer deletedBy;
	
	@Column(name = "deleted_on")
	private LocalDateTime deletedOn;
	
}