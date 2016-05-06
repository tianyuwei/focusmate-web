package com.focusmate.datasource.repository;

import com.focusmate.datasource.entities.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation, Integer> {

    public UserInformation findByUserNameAndPassword(String userName, String password);
}
