package com.focusmate.datasource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.focusmate.datasource.entities.SiteRevenue;

@Repository
public interface SiteRevenueRepository extends JpaRepository<SiteRevenue, Integer> {

}
