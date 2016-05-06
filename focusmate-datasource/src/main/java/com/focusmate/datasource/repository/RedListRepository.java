package com.focusmate.datasource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.focusmate.datasource.entities.RedList;

@Repository
public interface RedListRepository extends JpaRepository<RedList, Integer> {

}
