package com.roshan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
public class UsersEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId", nullable = false, unique = true)
    private Integer userId;

    @Column(name = "UserName", nullable = false, unique = true)
    private String userName;

    @Column(name = "FirstName", nullable = false)
    private String firstName;

    @Column(name = "LastName", nullable = false)
    private String lastName;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "PhoneNumber", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "create_timestamp", nullable = false)
    private OffsetDateTime createTimestamp;

    @Column(name = "update_timestamp", nullable = false)
    private OffsetDateTime updateTimestamp;
}
