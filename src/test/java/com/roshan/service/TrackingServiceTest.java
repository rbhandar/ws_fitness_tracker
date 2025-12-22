package com.roshan.service;

import com.roshan.dto.TrackingSystemDTO;
import com.roshan.dto.UserDTO;
import com.roshan.entity.FitnessTrackingEntity;
import com.roshan.entity.UsersEntity;
import com.roshan.repository.TrackingSystemRepository;
import com.roshan.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrackingServiceTest {

    @InjectMocks
    private TrackingService trackingService;

    @Mock
    private TrackingSystemRepository trackingSystemRepository;

    @Mock
    private UsersRepository usersRepository;

    @Test
    public void testGetTrackingDataByUserIdAndType() {
        // Arrange
        Integer userId = 123;
        String userName = "testuser";
        String trackingType = "steps";

        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(userName);
        userDTO.setUserId(userId);

        TrackingSystemDTO trackingSystemDTO = new TrackingSystemDTO();
        trackingSystemDTO.setUser(userDTO);
        trackingSystemDTO.setTrackingType(trackingType);
        trackingSystemDTO.setTrackingName("Daily Steps");
        trackingSystemDTO.setStatus("Active");
        trackingSystemDTO.setTotalDistance("3 km");
        trackingSystemDTO.setStartTime("2024-06-01T08:00:00Z");
        trackingSystemDTO.setEndTime("2024-06-01T20:00:00Z");
        trackingSystemDTO.setAveragePace("N/A");

        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setUserId(userId);
        usersEntity.setUserName(userName);

        when(usersRepository.findByUserName(userName)).thenReturn(usersEntity);
        
        FitnessTrackingEntity fitnessTrackingEntity = new FitnessTrackingEntity();
        fitnessTrackingEntity.setUsersEntity(usersEntity);
        fitnessTrackingEntity.setTrackingType(trackingType);
        fitnessTrackingEntity.setTrackingName("Daily Steps");
        fitnessTrackingEntity.setStatus("Active");
        fitnessTrackingEntity.setTotalDistance("3 km");
        fitnessTrackingEntity.setStartTime(OffsetDateTime.parse("2024-06-01T08:00:00Z"));
        fitnessTrackingEntity.setEndTime(OffsetDateTime.parse("2024-06-01T20:00:00Z"));
        fitnessTrackingEntity.setAveragePace("N/A");

        when(trackingSystemRepository.findByUserIdAndTrackingType(userId, trackingType))
                .thenReturn(fitnessTrackingEntity);

        // Act
        TrackingSystemDTO result = trackingService.getTrackingDataByUserNameAndType(userName, trackingType);
        // Assert
        assertNotNull(result);
        assertEquals(trackingSystemDTO.getUser().getUserId(), result.getUser().getUserId());
        assertEquals(trackingSystemDTO.getTotalDistance(), result.getTotalDistance());

    }

}