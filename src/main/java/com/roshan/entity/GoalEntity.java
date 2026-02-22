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
@Table(name = "Goal")
public class GoalEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GoalId", nullable = false, unique = true)
    private Integer goalId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "UserId", referencedColumnName = "UserId", nullable = false)
    private UsersEntity usersEntity;

    @Column(name = "GoalType", nullable = false, length = 15)
    private String goalType;

    @Column(name = "GoalValue", nullable = false, length = 15)
    private String goalValue;

    @Column(name = "StartDate", nullable = false)
    private LocalDate startDate;

    @Column(name = "EndDate")
    private LocalDate endDate;
}

