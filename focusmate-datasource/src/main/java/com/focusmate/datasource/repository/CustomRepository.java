package com.focusmate.datasource.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.focusmate.datasource.entities.Custom;

public interface CustomRepository extends JpaRepository<Custom, Integer> {

    public Custom findByUserInformationUserId(Integer userId);

}
