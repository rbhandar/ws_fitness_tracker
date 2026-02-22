package com.roshan.service;

import com.roshan.dto.FoodItemDTO;
import com.roshan.entity.FoodItemEntity;
import com.roshan.repository.FoodItemRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class FoodItemService {

    private FoodItemRepository foodItemRepository;

    public FoodItemDTO createFoodItem(FoodItemDTO foodItemDTO) {
        log.info("Creating food item: {}", foodItemDTO);
        // Convert DTO to Entity
        FoodItemEntity foodItemEntity = new FoodItemEntity();
        foodItemEntity.setFoodName(foodItemDTO.getFoodName());
        foodItemEntity.setFoodCategory(foodItemDTO.getFoodCategory());
        foodItemEntity.setFoodDescription(foodItemDTO.getFoodDescription());


        // Save to database
        FoodItemEntity savedEntity = foodItemRepository.save(foodItemEntity);

        // Convert back to DTO
        FoodItemDTO savedDTO = new FoodItemDTO();
        savedDTO.setFoodId(savedEntity.getFoodId());
        savedDTO.setFoodName(savedEntity.getFoodName());
        savedDTO.setFoodCategory(savedEntity.getFoodCategory());
        savedDTO.setFoodDescription(savedEntity.getFoodDescription());

        log.info("Created food item with ID: {}", savedDTO.getFoodId());
        return savedDTO;
    }

}
