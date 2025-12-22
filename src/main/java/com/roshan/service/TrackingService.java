package com.roshan.service;

import com.roshan.dto.TrackingSystemDTO;
import com.roshan.dto.UserDTO;
import com.roshan.entity.FitnessTrackingEntity;
import com.roshan.entity.UsersEntity;
import com.roshan.repository.TrackingSystemRepository;
import com.roshan.repository.UsersRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Log4j2
@AllArgsConstructor
@Service
public class TrackingService {

    private final UsersRepository usersRepository;
    private final TrackingSystemRepository trackingSystemRepository;


    public void startTracking(String userId, String trackingType) {
        // Logic to start tracking
        if (trackingType.equals("RUNNING")) {
            // Start running tracking by recording GPS data and time
            // Save tracking data to database by using userId, trackingType, and timeMillis
            // map to entity and save
        } else if (trackingType.equals("CYCLING")) {
            // Start cycling tracking
        }
    }


    public TrackingSystemDTO getTrackingDataByUserNameAndType(String userName, String trackingType) {
        UsersEntity usersEntity = usersRepository.findByUserName(userName);
        if (usersEntity == null) {
            log.warn("User not found with userName: {}", userName);
            return null;
        }

        Integer userId = usersEntity.getUserId();
        FitnessTrackingEntity trackingData = trackingSystemRepository.findByUserIdAndTrackingType(userId, trackingType);
        if (trackingData == null) {
            log.warn("Tracking data not found for userId: {} and trackingType: {}", userId, trackingType);
            return null;
        }
        // Map entity to DTO
        TrackingSystemDTO trackingSystemDTO = new TrackingSystemDTO();
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(usersEntity.getUserId());
        userDTO.setUserName(usersEntity.getUserName());
        userDTO.setFirstName(usersEntity.getFirstName());
        userDTO.setLastName(usersEntity.getLastName());
        userDTO.setEmail(usersEntity.getEmail());
        trackingSystemDTO.setUser(userDTO);
        trackingSystemDTO.setTrackingId(trackingData.getTrackingId());
        trackingSystemDTO.setTrackingName(trackingData.getTrackingName());
        trackingSystemDTO.setTrackingType(trackingData.getTrackingType());
        trackingSystemDTO.setStatus(trackingData.getStatus());
        trackingSystemDTO.setTotalDistance(trackingData.getTotalDistance());
        trackingSystemDTO.setStartTime(String.valueOf(trackingData.getStartTime()));
        trackingSystemDTO.setEndTime(String.valueOf(trackingData.getEndTime()));
        trackingSystemDTO.setAveragePace(trackingData.getAveragePace());
        return trackingSystemDTO;
    }

    public FitnessTrackingEntity startTrackingData(TrackingSystemDTO trackingSystemDTO) {
        FitnessTrackingEntity entity = new FitnessTrackingEntity();
        // Map DTO to entity

        // Get UsersEntity by userId
        UsersEntity usersEntity = usersRepository.findById(trackingSystemDTO.getUser().getUserId()).orElse(new UsersEntity());
        if (usersEntity.getUserId() == null) {
            log.warn("User not found with id: {}", trackingSystemDTO.getUser().getUserId());
            return null;
        }
        entity.setUsersEntity(usersEntity);

        entity.setTrackingName(trackingSystemDTO.getTrackingName());
        entity.setTrackingType(trackingSystemDTO.getTrackingType());
        entity.setStatus(trackingSystemDTO.getStatus());
        entity.setTotalDistance(trackingSystemDTO.getTotalDistance());
        entity.setStartTime(OffsetDateTime.parse(trackingSystemDTO.getStartTime()));
        entity.setEndTime(OffsetDateTime.parse(trackingSystemDTO.getEndTime()));
        entity.setAveragePace(trackingSystemDTO.getAveragePace());
        return trackingSystemRepository.save(entity);
    }

    public FitnessTrackingEntity completeTracking(Integer trackingId, TrackingSystemDTO trackingSystemDTO) {
        FitnessTrackingEntity trackingEntity = trackingSystemRepository.findById(trackingId).orElse(null);
        if (trackingEntity == null) {
            log.warn("Tracking entity not found with id: {}", trackingId);
            return null;
        }

        if (!"IN_PROGRESS".equals(trackingEntity.getStatus())) {
            throw new IllegalStateException("Tracking is not in progress");
        }

        OffsetDateTime end = OffsetDateTime.parse(trackingSystemDTO.getEndTime());
        trackingEntity.setEndTime(end);
        trackingEntity.setAveragePace(trackingSystemDTO.getAveragePace());
        trackingEntity.setTotalDistance(trackingSystemDTO.getTotalDistance());
        trackingEntity.setStatus("COMPLETED");
        return trackingSystemRepository.save(trackingEntity);
    }

}
