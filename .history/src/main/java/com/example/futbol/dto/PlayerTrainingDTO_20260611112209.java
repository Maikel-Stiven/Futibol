package com.example.futbol.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PlayerTrainingDTO {
    private String name;
    private double shotPower;
    private double speed;
    private int effectivePasses;
}