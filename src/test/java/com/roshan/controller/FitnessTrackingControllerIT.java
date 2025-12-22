package com.roshan.controller;

import com.roshan.dto.TrackingSystemDTO;
import com.roshan.dto.UserDTO;
import com.roshan.entity.FitnessTrackingEntity;
import com.roshan.entity.UsersEntity;
import com.roshan.repository.TrackingSystemRepository;
import com.roshan.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@org.springframework.test.context.ActiveProfiles("test")
class FitnessTrackingControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TrackingSystemRepository trackingSystemRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Test
    void testCompleteTracking_returns200_happyPath() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(1);

        Integer trackingId = 1;
        TrackingSystemDTO trackingSystemDTORequest = new TrackingSystemDTO(); // controller doesn't use it, but endpoint expects JSON
        trackingSystemDTORequest.setTrackingType("Biking");
        trackingSystemDTORequest.setUser(userDTO);
        trackingSystemDTORequest.setStartTime(String.valueOf(OffsetDateTime.now().minusHours(1)));
        trackingSystemDTORequest.setEndTime(String.valueOf(OffsetDateTime.now()));
        trackingSystemDTORequest.setStatus("COMPLETED");
        trackingSystemDTORequest.setAveragePace("5 mph");
        trackingSystemDTORequest.setTotalDistance("15 miles");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TrackingSystemDTO> request = new HttpEntity<>(trackingSystemDTORequest, headers);

        // Act
        ResponseEntity<FitnessTrackingEntity> response = restTemplate
                .withBasicAuth("user", "test123")
                .exchange(
                "/fitness/{id}/complete-tracking", HttpMethod.PUT, request, FitnessTrackingEntity.class, trackingId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getTrackingId());
        assertEquals("COMPLETED", response.getBody().getStatus());
    }

    @Test
    void completeTrackingReturns404_whenNotFound() {
        // Arrange
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setUserName("test");


        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(1);

        TrackingSystemDTO trackingSystemDTORequest = new TrackingSystemDTO(); // controller doesn't use it, but endpoint expects JSON
        trackingSystemDTORequest.setTrackingType("Biking");
        trackingSystemDTORequest.setUser(userDTO);
        trackingSystemDTORequest.setStartTime(String.valueOf(OffsetDateTime.now().minusHours(1)));
        trackingSystemDTORequest.setEndTime(String.valueOf(OffsetDateTime.now()));
        trackingSystemDTORequest.setStatus("COMPLETED");
        trackingSystemDTORequest.setAveragePace("5 mph");
        trackingSystemDTORequest.setTotalDistance("15 miles");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TrackingSystemDTO> request = new HttpEntity<>(trackingSystemDTORequest, headers);

        // Act
        ResponseEntity<FitnessTrackingEntity> response = restTemplate
                .withBasicAuth("user", "test123")
                .exchange(
                        "/fitness/{id}/complete", HttpMethod.PUT, request, FitnessTrackingEntity.class, 9999);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testStartTracking_savesTracking_returns200_happyPath() {
        // Arrange
        //Reset SQL data before test
        trackingSystemRepository.deleteAll();

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(1);

        TrackingSystemDTO trackingSystemDTORequest = new TrackingSystemDTO(); // controller doesn't use it, but endpoint expects JSON
        trackingSystemDTORequest.setTrackingType("Biking");
        trackingSystemDTORequest.setTrackingName("Morning Biking");
        trackingSystemDTORequest.setUser(userDTO);
        trackingSystemDTORequest.setStartTime(String.valueOf(OffsetDateTime.now().minusHours(1)));
        trackingSystemDTORequest.setEndTime(String.valueOf(OffsetDateTime.now()));
        trackingSystemDTORequest.setStatus("IN_PROGRESS");
        trackingSystemDTORequest.setAveragePace("5 mph");
        trackingSystemDTORequest.setTotalDistance("15 miles");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TrackingSystemDTO> request = new HttpEntity<>(trackingSystemDTORequest, headers);

        // Act
        ResponseEntity<FitnessTrackingEntity> response = restTemplate
                .withBasicAuth("user", "test123")
                .exchange(
                        "/fitness/start-tracking", HttpMethod.POST, request, FitnessTrackingEntity.class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getTrackingId());
        assertEquals("IN_PROGRESS", response.getBody().getStatus());
    }

    @Test
    void testStartTrackingReturns400_whenUserNotFound() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(9999); // Non-existent user ID

        TrackingSystemDTO trackingSystemDTORequest = new TrackingSystemDTO(); // controller doesn't use it, but endpoint expects JSON
        trackingSystemDTORequest.setTrackingType("Biking");
        trackingSystemDTORequest.setTrackingName("Morning Biking");
        trackingSystemDTORequest.setUser(userDTO);
        trackingSystemDTORequest.setStartTime(String.valueOf(OffsetDateTime.now().minusHours(1)));
        trackingSystemDTORequest.setEndTime(String.valueOf(OffsetDateTime.now()));
        trackingSystemDTORequest.setStatus("IN_PROGRESS");
        trackingSystemDTORequest.setAveragePace("5 mph");
        trackingSystemDTORequest.setTotalDistance("15 miles");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TrackingSystemDTO> request = new HttpEntity<>(trackingSystemDTORequest, headers);

        // Act
        ResponseEntity<FitnessTrackingEntity> response = restTemplate
                .withBasicAuth("user", "test123")
                .exchange(
                        "/fitness/start-tracking", HttpMethod.POST, request, FitnessTrackingEntity.class);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testGetTrackingDataByUserAndTrackingType_returns200_happyPath() {
        // Arrange
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept", "application/json");
        // Act
        ResponseEntity<TrackingSystemDTO> response = restTemplate
                .withBasicAuth("user", "test123")
                .exchange("/fitness/getTrackingDataByUserNameAndTrackingType/{userName}/{TrackingType}",
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        TrackingSystemDTO.class,
                        "test",
                        "Biking");
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getTrackingId());
        assertEquals("Morning Biking", response.getBody().getTrackingName());
        assertEquals("Biking", response.getBody().getTrackingType());
    }

    @Test
    void testGetTrackingDataByUserAndTrackingTypeReturns404_whenUserNameNotFound() {
        // Arrange
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept", "application/json");

        // Act
        ResponseEntity<TrackingSystemDTO> response = restTemplate
                .withBasicAuth("user", "test123")
                .exchange("/fitness/getTrackingDataByUserNameAndTrackingType/{userName}/{TrackingType}",
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        TrackingSystemDTO.class,
                        "nonexistentuser",
                        "Biking");
        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetTrackingDataByUserAndTrackingTypeReturns404_whenTrackingRecordNotFound() {
        // Arrange
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept", "application/json");

        // Act
        ResponseEntity<TrackingSystemDTO> response = restTemplate
                .withBasicAuth("user", "test123")
                .exchange("/fitness/getTrackingDataByUserNameAndTrackingType/{userName}/{TrackingType}",
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        TrackingSystemDTO.class,
                        "test",
                        "NonExistentTrackingType");
        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}