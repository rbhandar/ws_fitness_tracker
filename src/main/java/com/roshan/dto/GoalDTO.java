package com.roshan.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GoalDTO {
    private Integer goalId;
    private UserDTO user;
    private String goalType;
    private String goalValue;
    private LocalDate startDate;
    private LocalDate endDate;
}