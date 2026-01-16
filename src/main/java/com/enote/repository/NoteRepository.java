package com.enote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.enote.model.NoteEntity;

public interface NoteRepository extends JpaRepository<NoteEntity, Integer> {
	@Modifying
	@Query(value = "UPDATE noteEntity SET status = 9, deleted_on = NOW(), deleted_by = :deletedBy WHERE id = :noteId", nativeQuery = true)
	void softDeleteById(@Param("noteId") Integer noteId, @Param("deletedBy") Integer deletedBy);

}
