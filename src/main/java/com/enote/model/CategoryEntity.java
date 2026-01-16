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
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "category")
public class CategoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	private Short status;
	private Boolean isDeleted;
	@CreatedBy
	@Column(updatable = false)
	private Integer createdBy;
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdOn;
	@LastModifiedBy
	@Column(insertable  = false)
	private Integer updatedBy;
	@LastModifiedDate
	@Column(insertable  = false)
	private LocalDateTime updatedOn;
	
	private Integer deletedBy;
	private LocalDateTime deletedOn;
	

}
