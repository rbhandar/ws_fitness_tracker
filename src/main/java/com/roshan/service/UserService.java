package com.roshan.service;

import com.roshan.dto.UserDTO;
import com.roshan.entity.UsersEntity;
import com.roshan.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class UserService {

    private final UsersRepository usersRepository;


    public UsersEntity createUser(UserDTO userDTO) {
        // Business logic to create a user
        UsersEntity usersEntity = new UsersEntity();
        //usersEntity.setUserId(userDTO.getUserId());
        usersEntity.setUserName(userDTO.getUserName());
        usersEntity.setFirstName(userDTO.getFirstName());
        usersEntity.setLastName(userDTO.getLastName());
        usersEntity.setEmail(userDTO.getEmail());
        usersEntity.setPhoneNumber(userDTO.getPhoneNumber());
        usersEntity.setCreateTimestamp(OffsetDateTime.now());
        usersEntity.setUpdateTimestamp(OffsetDateTime.now());
        return usersRepository.save(usersEntity);
    }

}
