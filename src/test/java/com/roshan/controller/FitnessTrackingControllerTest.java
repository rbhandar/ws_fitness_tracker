package com.roshan.controller;

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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FitnessTrackingControllerTest {

    @InjectMocks
    private FitnessTrackingController fitnessTrackingController;


    @Mock
    private UsersRepository usersRepository;

    @Mock
    private TrackingSystemRepository trackingSystemRepository;

    @Test
    void getTrackingDataByUserAndTrackingTypeTest() {
        // Arrange
        String userName = "user1";
        String trackingType = "steps";
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setUserId(1);
        usersEntity.setUserName(userName);

        FitnessTrackingEntity fitnessTrackingEntity = new FitnessTrackingEntity();
        fitnessTrackingEntity.setTrackingId(100);
        fitnessTrackingEntity.setTrackingType(trackingType);
        fitnessTrackingEntity.setTrackingName("Daily Steps");
        fitnessTrackingEntity.setStatus("Active");
        fitnessTrackingEntity.setTotalDistance("5 km");
        fitnessTrackingEntity.setStartTime(java.time.OffsetDateTime.parse("2024-06-01T08:00:00Z"));
        fitnessTrackingEntity.setEndTime(java.time.OffsetDateTime.parse("2024-06-01T20:00:00Z"));
        fitnessTrackingEntity.setAveragePace("N/A");
        fitnessTrackingEntity.setUsersEntity(usersEntity);
        when(usersRepository.findByUserName(userName)).thenReturn(usersEntity);
        when(trackingSystemRepository.findByUserIdAndTrackingType(1, trackingType))
                .thenReturn(fitnessTrackingEntity);
        // Act
        var response = fitnessTrackingController.getTrackingDataByUserAndTrackingType(userName, trackingType);
        // Assert
        assertNotNull(response);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        TrackingSystemDTO dto = response.getBody();
        assertNotNull(dto);
        assertEquals(100, dto.getTrackingId());
        assertEquals("Daily Steps", dto.getTrackingName());
        assertEquals("steps", dto.getTrackingType());
        assertEquals("Active", dto.getStatus());
        assertEquals("5 km", dto.getTotalDistance());
        assertEquals("N/A", dto.getAveragePace());
        assertNotNull(dto.getUser());
        assertEquals(1, dto.getUser().getUserId());
        assertEquals("user1", dto.getUser().getUserName());

    }

    @Test
    void saveTrackingDataTest() {
        // Arrange
        TrackingSystemDTO trackingSystemDTO = new TrackingSystemDTO();
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(1);
        trackingSystemDTO.setUser(userDTO);
        trackingSystemDTO.setTrackingName("Evening Walk");
        trackingSystemDTO.setTrackingType("walk");
        trackingSystemDTO.setStatus("Completed");
        trackingSystemDTO.setTotalDistance("3 km");
        trackingSystemDTO.setStartTime("2024-06-01T18:00:00Z");
        trackingSystemDTO.setEndTime("2024-06-01T19:00:00Z");
        trackingSystemDTO.setAveragePace("10 min/km");

        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setUserId(1);
        when(usersRepository.findById(1)).thenReturn(Optional.of(usersEntity));

        FitnessTrackingEntity savedEntity = new FitnessTrackingEntity();
        savedEntity.setTrackingId(1);
        savedEntity.setUsersEntity(usersEntity);
        savedEntity.setTrackingName("Evening Walk");
        savedEntity.setTrackingType("walk");
        savedEntity.setStatus("Completed");
        savedEntity.setTotalDistance("3 km");
        savedEntity.setStartTime(java.time.OffsetDateTime.parse("2024-06-01T18:00:00Z"));
        savedEntity.setEndTime(java.time.OffsetDateTime.parse("2024-06-01T19:00:00Z"));
        savedEntity.setAveragePace("10 min/km");

        when(trackingSystemRepository.save(org.mockito.ArgumentMatchers.any(FitnessTrackingEntity.class)))
                .thenReturn(savedEntity);
        // Act
        var response = fitnessTrackingController.saveTrackingData(trackingSystemDTO);
        // Assert
        assertNotNull(response);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        FitnessTrackingEntity resultEntity = response.getBody();
        assertNotNull(resultEntity);
        assertEquals(1, resultEntity.getTrackingId());
        assertEquals("walk", resultEntity.getTrackingType());
        assertEquals("Completed", resultEntity.getStatus());
        assertEquals("3 km", resultEntity.getTotalDistance());
    }

}