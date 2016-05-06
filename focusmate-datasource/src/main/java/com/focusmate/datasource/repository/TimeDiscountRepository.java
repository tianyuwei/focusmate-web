package com.focusmate.datasource.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.focusmate.datasource.entities.TimeDiscount;

public interface TimeDiscountRepository extends JpaRepository<TimeDiscount, Integer> {
    List<TimeDiscount> findByProductId(Integer productId);
}
