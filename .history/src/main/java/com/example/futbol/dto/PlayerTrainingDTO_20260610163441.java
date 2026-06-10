package com.example.futbol.dto;

public class PlayerTrainingDTO {
    private String name;
    private double shotPower;
    private double speed;
    private int effectivePasses;

    public double calculateScore(){
        return (this.shotPower * 20) + (this.speed * 30) + (this.effectivePasses + 50);
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name =  name;
    }

    public double getShotPower(){
        return shotPower;
    }

    public void setShotPower(double shotPower){
        this.shotPower = shotPower;
    }
}
