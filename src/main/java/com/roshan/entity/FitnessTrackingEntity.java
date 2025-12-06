package com.roshan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "Fitness_Tracking")
@NoArgsConstructor
@AllArgsConstructor
public class FitnessTrackingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TrackingID", nullable = false, unique = true)
    private Integer trackingId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "UserID", referencedColumnName = "UserID", nullable = false)
    private UsersEntity usersEntity;

    @Column(name = "TrackingName", nullable = false)
    private String trackingName;

    @Column(name = "TrackingType", nullable = false)
    private String trackingType;

    @Column(name = "Status", nullable = false)
    private String status;

    @Column(name = "TotalDistance", nullable = false)
    private String totalDistance;

    @Column(name = "AveragePace", nullable = false)
    private String averagePace;

    @Column(name = "StartTime", nullable = false)
    private OffsetDateTime startTime;

    @Column(name = "EndTime")
    private OffsetDateTime endTime;

}
