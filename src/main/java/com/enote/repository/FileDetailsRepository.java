package com.enote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enote.model.FileDetailsEntity;

@Repository
public interface FileDetailsRepository extends JpaRepository<FileDetailsEntity, Integer> {

}
