package com.focusmate.datasource.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.focusmate.datasource.entities.WxTransaction;

public interface WxTransactionRepository extends JpaRepository<WxTransaction, Integer> {
    WxTransaction findByTranscationId(String transactionId);

    List<WxTransaction> findByStatus(Integer status);

    List<WxTransaction> findByStatusAndStationId(Integer status, Integer stationId);

}
