package com.roshan.repository;

import com.roshan.dto.UserDTO;
import com.roshan.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Integer> {

    UsersEntity findByUserName(String userName);
    UsersEntity findByUserNameAndEmail(String userName, String email);

}
