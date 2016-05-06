package com.focusmate.datasource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.focusmate.datasource.entities.CarList;

@Repository
public interface CarListRepository extends JpaRepository<CarList, Integer> {

}
