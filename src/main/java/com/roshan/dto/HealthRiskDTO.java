package com.roshan.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * DTO representing a HealthRisk record.
 */
@Data
public class HealthRiskDTO {
    private Integer riskId;
    private UserDTO user;
    private String riskLevel;
    private LocalDate calculatedDate;
    private String remarks;
}

