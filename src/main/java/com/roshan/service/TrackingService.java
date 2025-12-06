package com.roshan.service;

import com.roshan.dto.TrackingSystemDTO;
import com.roshan.dto.UserDTO;
import com.roshan.entity.FitnessTrackingEntity;
import com.roshan.entity.UsersEntity;
import com.roshan.repository.TrackingSystemRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class TrackingService {

    private TrackingSystemRepository trackingSystemRepository;

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

    public TrackingSystemDTO getTrackingDataByIdAndUserId(Integer trackingId, String userId) {
        // Logic to get tracking data by ID and User ID
        FitnessTrackingEntity entity = trackingSystemRepository.findById(trackingId).orElse(null);
        if (entity != null) {
            // Map entity to DTO and return
            TrackingSystemDTO dto = new TrackingSystemDTO();
            // set fields from entity to dto
            return dto;
        }
        return null;
    }

    public TrackingSystemDTO getTrackingDataByUserIdAndType(Integer userId, String trackingType) {
        // Logic to get tracking data by User ID and Type
        // This would typically involve a custom query in the repository
            FitnessTrackingEntity entity = trackingSystemRepository.findByUserIdAndTrackingType(userId, trackingType);
            if (entity != null) {
                // Map entity to DTO and return
                TrackingSystemDTO dto = new TrackingSystemDTO();

                UserDTO userDto = new UserDTO();
                userDto.setUserId(entity.getUsersEntity().getUserId());
                userDto.setUserName(entity.getUsersEntity().getUserName());
                userDto.setUserName(entity.getUsersEntity().getFirstName());
                userDto.setEmail(entity.getUsersEntity().getEmail());
                dto.setUser(userDto);
                dto.setTrackingId(entity.getTrackingId());
                dto.setTrackingName(entity.getTrackingName());
                dto.setTrackingType(entity.getTrackingType());
                dto.setStatus(entity.getStatus());
                dto.setTotalDistance(entity.getTotalDistance());
                dto.setStartTime(String.valueOf(entity.getStartTime()));
                dto.setEndTime(String.valueOf(entity.getEndTime()));
                dto.setAveragePace(entity.getAveragePace());
                return dto;
            }
        return null;
    }

    public TrackingSystemDTO getTrackingDataByUserIdAndTypeV2(Integer userId, String trackingType) {
        // Logic to get tracking data by User ID and Type
        // This would typically involve a custom query in the repository
       // FitnessTrackingEntity entity = trackingSystemRepository.findByUserIdAndTrackingType(userId, trackingType);
         FitnessTrackingEntity entity = new FitnessTrackingEntity(); // Placeholder for actual repository call
            entity.setTrackingId(1);
            UsersEntity usersEntity = new UsersEntity();
            usersEntity.setUserId(userId);
            entity.setUsersEntity(usersEntity);
            entity.setTrackingName("Morning Run");
            entity.setTrackingType(trackingType);
            entity.setStatus("Completed");
            entity.setTotalDistance("5 km");
            entity.setStartTime(OffsetDateTime.parse("2024-06-01T06:00:00Z"));
            entity.setEndTime(OffsetDateTime.parse("2024-06-01T06:30:00Z"));
            entity.setAveragePace("6 min/km");

        if (entity != null) {
            // Map entity to DTO and return
            TrackingSystemDTO dto = new TrackingSystemDTO();

            UserDTO userDto = new UserDTO();
            userDto.setUserId(entity.getUsersEntity().getUserId());

            // set fields from entity to dto
            dto.setTrackingId(entity.getTrackingId());
            dto.setUser(userDto);
            dto.setTrackingName(entity.getTrackingName());
            dto.setTrackingType(entity.getTrackingType());
            dto.setStatus(entity.getStatus());
            dto.setTotalDistance(entity.getTotalDistance());
            dto.setStartTime(String.valueOf(entity.getStartTime()));
            dto.setEndTime(String.valueOf(entity.getEndTime()));
            dto.setAveragePace(entity.getAveragePace());
            return dto;
        }
        return null;
    }

}
