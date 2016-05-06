package com.focusmate.datasource.repository;

import com.focusmate.datasource.entities.IntegralRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntegralRecordRepository extends JpaRepository<IntegralRecord, Integer> {

}
