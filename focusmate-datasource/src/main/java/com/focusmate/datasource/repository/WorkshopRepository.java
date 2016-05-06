package com.focusmate.datasource.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.focusmate.datasource.entities.Workshop;

public interface WorkshopRepository extends JpaRepository<Workshop, Integer> {

}
