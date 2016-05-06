package com.focusmate.datasource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.focusmate.datasource.entities.ChargeRecord;
import com.focusmate.datasource.entities.ChargeRecord.ChargeRecordState;

@Repository
public interface ChargeRecordRepository extends JpaRepository<ChargeRecord, Integer> {

    public ChargeRecord findByUserIdAndState(Integer userId, ChargeRecordState state);

    public ChargeRecord findByUserIdAndToken(Integer userId, String token);

}
