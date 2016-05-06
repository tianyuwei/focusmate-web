package com.focusmate.datasource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.focusmate.datasource.entities.CarInformation;

@Repository
public interface CarInformationRepository extends JpaRepository<CarInformation, Integer> {

}
