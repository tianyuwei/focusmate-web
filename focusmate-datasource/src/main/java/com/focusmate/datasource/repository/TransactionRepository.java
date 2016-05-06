package com.focusmate.datasource.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.focusmate.datasource.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    public Transaction findByOrderCode(String orderCode);

}
