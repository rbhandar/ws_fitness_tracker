package com.roshan.controller;

import com.roshan.dto.TrackingSystemDTO;
import com.roshan.dto.UserDTO;
import com.roshan.entity.FitnessTrackingEntity;

import com.roshan.entity.UsersEntity;
import com.roshan.repository.TrackingSystemRepository;
import com.roshan.repository.UsersRepository;
import com.roshan.service.TrackingService;
import com.roshan.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/fitness")
public class FitnessTrackingController {

    private final TrackingService trackingService;
    private final TrackingSystemRepository trackingSystemRepository;
    private final UsersRepository usersRepository;
    private final UserService userService;

    @GetMapping("/getTrackingDataByUserNameAndTrackingType/{userName}/{TrackingType}")
    public ResponseEntity<TrackingSystemDTO> getTrackingDataByUserAndTrackingType(@PathVariable String userName, @PathVariable String TrackingType) {
        log.info("Fetching tracking data for userName: {} and trackingType: {}", userName, TrackingType);
         //step 1: Fetch UsersEntity by userName to grab the userId
        // step 2: Use userId to fetch tracking data
        UsersEntity usersEntity = usersRepository.findByUserName(userName);
        if (usersEntity == null) {
            log.warn("User not found with userName: {}", userName);
            return ResponseEntity.notFound().build();
        }
        Integer userId = usersEntity.getUserId();
        FitnessTrackingEntity trackingData = trackingSystemRepository.findByUserIdAndTrackingType(userId, TrackingType);
        if (trackingData == null) {
            log.warn("Tracking data not found for userId: {} and trackingType: {}", userId, TrackingType);
            return ResponseEntity.notFound().build();
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
        return ResponseEntity.ok(trackingSystemDTO);

    }

    @PostMapping(path = "/savetrackingdata", consumes = "application/json")
    public ResponseEntity<FitnessTrackingEntity> saveTrackingData(@RequestBody TrackingSystemDTO trackingSystemDTO) {
        log.info("Saving tracking data for userId: {}", trackingSystemDTO.getUser().getUserId());
        // Placeholder implementation
        FitnessTrackingEntity entity = new FitnessTrackingEntity();
        // Map DTO to entity

        // Get UsersEntity by userId
        UsersEntity usersEntity = usersRepository.findById(trackingSystemDTO.getUser().getUserId()).orElse(new UsersEntity());

        if (usersEntity == null) {
            log.warn("User not found with userId: {}", trackingSystemDTO.getUser().getUserId());
            return ResponseEntity.badRequest().build();
        }
        entity.setUsersEntity(usersEntity);

        entity.setTrackingName(trackingSystemDTO.getTrackingName());
        entity.setTrackingType(trackingSystemDTO.getTrackingType());
        entity.setStatus(trackingSystemDTO.getStatus());
        entity.setTotalDistance(trackingSystemDTO.getTotalDistance());
        entity.setStartTime(OffsetDateTime.parse(trackingSystemDTO.getStartTime()));
        entity.setEndTime(OffsetDateTime.parse(trackingSystemDTO.getEndTime()));
        entity.setAveragePace(trackingSystemDTO.getAveragePace());
        // Simulate saving entity
        FitnessTrackingEntity savedEntity = trackingSystemRepository.save(entity);
        return ResponseEntity.ok(savedEntity);

    }

    @PostMapping(path = "/createuser", consumes = "application/json")
    public ResponseEntity<UsersEntity> createUser(@RequestBody UserDTO userDTO) {
        log.info("Creating user with userName: {}", userDTO.getUserName());

        UsersEntity createdUser = userService.createUser(userDTO);
        if (createdUser == null) {
            log.warn("Failed to create user with userName: {}", userDTO.getUserName());
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(createdUser);

 }
}

