package com.roshan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "HealthRisk")
public class HealthRiskEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RiskId", nullable = false, unique = true)
    private Integer riskId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "UserId", referencedColumnName = "UserId", nullable = false)
    private UsersEntity usersEntity;

    @Column(name = "RiskLevel", nullable = false, length = 50)
    private String riskLevel;

    @Column(name = "CalculatedDate", nullable = false)
    private LocalDate calculatedDate;

    @Column(name = "Remarks", nullable = false, length = 25)
    private String remarks;
}

