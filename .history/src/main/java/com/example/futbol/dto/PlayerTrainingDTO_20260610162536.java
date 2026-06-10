package com.example.futbol.dto;

public class PlayerTrainingDTO {
    private String name;
    private double shotPower;
    private double speed;
    private int effectivePasses;

    public double calculateScore(){
        return (this.shotPower * 20) + (this.speed * 30) + (this.effectivePasses + 50);
    }
}
