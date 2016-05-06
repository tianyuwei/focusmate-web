package com.focusmate.datasource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.focusmate.datasource.entities.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {

    public Station findByUserName(String username);

    public Station findByUserNameAndPassword(String username, String password);

}
