package com.roshan.service;

import com.roshan.dto.HealthRiskDTO;
import com.roshan.mapper.HealthRiskMapper;
import com.roshan.repository.HealthRishRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@AllArgsConstructor
@Service
public class HealthRiskService {

    private final HealthRishRepository healthRishRepository;

    public HealthRiskDTO createHealthRisk(HealthRiskDTO healthRiskDTO) {
        if (healthRiskDTO == null) return null;

        // Convert DTO to Entity using mapper
        var healthRiskEntity = HealthRiskMapper.convertToEntity(healthRiskDTO);

        // Save to repository
        var savedEntity = healthRishRepository.save(healthRiskEntity);

        // Convert back to DTO and return
        return HealthRiskMapper.convertToDTO(savedEntity);
    }
}
