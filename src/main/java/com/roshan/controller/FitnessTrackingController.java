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

        TrackingSystemDTO trackingSystemDTO = trackingService.getTrackingDataByUserNameAndType(userName, TrackingType);

        if (trackingSystemDTO == null) {
            log.warn("User not found with userName: {}", userName);
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(trackingSystemDTO);
    }

    @PostMapping(path = "/start-tracking", consumes = "application/json")
    public ResponseEntity<FitnessTrackingEntity> startTracking(@RequestBody TrackingSystemDTO trackingSystemDTO) {
        log.info("Saving tracking data for userId: {}", trackingSystemDTO.getUser().getUserId());

        FitnessTrackingEntity savedEntity = trackingService.startTrackingData(trackingSystemDTO);
        if (savedEntity == null) {
            log.warn("Failed to save tracking data for userId: {}", trackingSystemDTO.getUser().getUserId());
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(savedEntity);

    }

    @PutMapping(path = "/{id}/complete-tracking", consumes = "application/json")
    public ResponseEntity<FitnessTrackingEntity> completeTracking(@PathVariable Integer id, @RequestBody TrackingSystemDTO trackingSystemDTO) {
        log.info("Completing tracking with id: {}", id);

        FitnessTrackingEntity updatedEntity = trackingService.completeTracking(id, trackingSystemDTO);
        if (updatedEntity == null) {
            log.warn("Tracking entity not found with id: {}", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedEntity);
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

