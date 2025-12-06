package com.roshan.repository;

import com.roshan.entity.FitnessTrackingEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TrackingSystemRepository extends JpaRepository<FitnessTrackingEntity, Integer> {

    @Query("SELECT fte FROM FitnessTrackingEntity fte WHERE fte.usersEntity.userId = :userId AND fte.trackingType = :trackingType")
    FitnessTrackingEntity findByUserIdAndTrackingType(@Param("userId") Integer userId, @Param("trackingType") String trackingType);

    FitnessTrackingEntity findByUsersEntity_UserIdAndTrackingType(Integer userId, String trackingType);
}
