package com.enote.model;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "file_detail")
@EntityListeners(AuditingEntityListener.class)
public class FileDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "upload_file_name", length = 250)
    private String uploadFileName;

    @Column(name = "display_file_name", length = 250)
    private String displayFileName;

    @Column(name = "original_file_name", length = 250)
    private String originalFileName;

    @Column(name = "path", length = 400)
    private String path;

    @Column(name = "file_size")
    private Integer fileSize;

    @Column(name = "status")
    private Short status = 1;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "created_on", updatable = false)
    private LocalDateTime createdOn;

    @Column(name = "updated_by")
    private Integer updatedBy;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @Column(name = "deleted_by")
    private Integer deletedBy;

    @Column(name = "deleted_on")
    private LocalDateTime deletedOn;

}
