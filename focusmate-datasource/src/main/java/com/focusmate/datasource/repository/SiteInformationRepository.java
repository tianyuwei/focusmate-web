package com.focusmate.datasource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.focusmate.datasource.entities.SiteInformation;

@Repository
public interface SiteInformationRepository extends JpaRepository<SiteInformation, Integer> {

}
