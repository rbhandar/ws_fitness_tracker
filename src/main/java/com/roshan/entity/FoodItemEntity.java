package com.roshan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FoodItem")
public class FoodItemEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FoodId", nullable = false, unique = true)
    private Integer foodId;

    @Column(name = "FoodName", nullable = false, length = 50)
    private String foodName;

    @Column(name = "FoodCategory", nullable = false, length = 25)
    private String foodCategory;

    @Column(name = "FoodDescription", length = 50)
    private String foodDescription;
}

