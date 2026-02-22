package com.roshan.mapper;

import com.roshan.dto.HealthRiskDTO;
import com.roshan.entity.HealthRiskEntity;
import com.roshan.entity.UsersEntity;

public class HealthRiskMapper {

    public static HealthRiskEntity toEntity(HealthRiskDTO dto) {
        if (dto == null) return null;

        HealthRiskEntity entity = new HealthRiskEntity();
        entity.setRiskId(dto.getRiskId());

        if (dto.getUser() != null) {
            UsersEntity user = new UsersEntity();
            user.setUserId(dto.getUser().getUserId());
            entity.setUsersEntity(user);
        }

        entity.setRiskLevel(dto.getRiskLevel());
        entity.setCalculatedDate(dto.getCalculatedDate());
        entity.setRemarks(dto.getRemarks());
        return entity;
    }

    public static HealthRiskDTO toDTO(HealthRiskEntity entity) {
        if (entity == null) return null;

        HealthRiskDTO dto = new HealthRiskDTO();
        dto.setRiskId(entity.getRiskId());

        if (entity.getUsersEntity() != null) {
            var userDto = new com.roshan.dto.UserDTO();
            userDto.setUserId(entity.getUsersEntity().getUserId());
            dto.setUser(userDto);
        }

        dto.setRiskLevel(entity.getRiskLevel());
        dto.setCalculatedDate(entity.getCalculatedDate());
        dto.setRemarks(entity.getRemarks());
        return dto;
    }

    public static HealthRiskEntity convertToEntity(HealthRiskDTO dto) {
        return toEntity(dto);
    }

    public static HealthRiskDTO convertToDTO(HealthRiskEntity entity) {
        return toDTO(entity);
    }
}
