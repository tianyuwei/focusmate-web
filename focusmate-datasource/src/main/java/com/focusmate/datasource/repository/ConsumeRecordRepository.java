package com.focusmate.datasource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.focusmate.datasource.entities.ConsumeRecord;

@Repository
public interface ConsumeRecordRepository extends JpaRepository<ConsumeRecord, Integer> {

}
