package com.focusmate.datasource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.focusmate.datasource.entities.RedRecord;

@Repository
public interface RedRecordRepository extends JpaRepository<RedRecord, Integer> {

}
