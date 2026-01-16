package com.enote.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.enote.model.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
Optional<CategoryEntity> findByName(String name);
@Modifying
@Query(value = "UPDATE category SET status = 9, is_deleted = true, deleted_on = NOW(), deleted_by = :deletedBy WHERE id = :categoryId", nativeQuery = true)
void softDeleteById(@Param("categoryId") Integer categoryId, @Param("deletedBy") Integer deletedBy);

}
