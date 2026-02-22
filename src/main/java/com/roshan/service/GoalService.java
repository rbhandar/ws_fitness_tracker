package com.roshan.service;

import com.roshan.dto.GoalDTO;
import com.roshan.entity.GoalEntity;
import com.roshan.repository.GoalRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@AllArgsConstructor
@Service
public class GoalService {

    private GoalRepository goalRepository;

    public GoalDTO createGoal(GoalDTO goalDTO) {

        //convert goalDTO to goal entity and save to database using GoalRepository
        GoalEntity goalEntity = new GoalEntity();
        goalEntity.setGoalType(goalDTO.getGoalType());
        goalEntity.setGoalValue(goalDTO.getGoalValue());
        goalEntity.setStartDate(goalDTO.getStartDate());
        goalEntity.setEndDate(goalDTO.getEndDate());

        GoalEntity savedGoal = goalRepository.save(goalEntity);

        // Convert the saved GoalEntity back to GoalDTO to return
        GoalDTO savedGoalDTO = new GoalDTO();
        savedGoalDTO.setGoalId(savedGoal.getGoalId());
        savedGoalDTO.setGoalType(savedGoal.getGoalType());
        savedGoalDTO.setGoalValue(savedGoal.getGoalValue());
        savedGoalDTO.setStartDate(savedGoal.getStartDate());
        savedGoalDTO.setEndDate(savedGoal.getEndDate());
        return savedGoalDTO;
    }
}
