package com.roshan.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class UserDTO {
    private Integer userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private OffsetDateTime createTimestamp;
    private OffsetDateTime updateTimestamp;
}
