package com.roshan.repository;

import com.roshan.entity.HealthRiskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthRishRepository extends JpaRepository<HealthRiskEntity, Integer> {
}
