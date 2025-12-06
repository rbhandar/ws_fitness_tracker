package com.roshan.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class TrackingSystemDTO {

    private Integer trackingId;
    private String trackingName;
    private String trackingType;
    private String status;
    private String TotalDistance;
    private String startTime;
    private String endTime;
    private String averagePace;
    private OffsetDateTime createTimestamp;
    private OffsetDateTime updateTimestamp;
    private UserDTO user;


}
