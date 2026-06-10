package com.example.futbol.service;

import com.example.futbol.dto.PlayerTrainingDTO;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class TeamService {
    
    private final Map<Integer, List<PlayerTrainingDTO>> trainingHistory = new ConcurrentHashMap<>(); 

    private static final int Required_trainings= 3;
    private static final int Starting_Players_Count= 5;

    public void saveTraining(int trainingNumber, List<PlayerTrainingDTO> players){
        this.trainingHistory.put(trainingNumber, players);
    }

    public List<Map<String, Object>> getStarting(){
        if (this.trainingHistory.size() < Required_trainings){
            throw new IllegalStateException("No hay suficiente información");
        }

        Map<String, Double> accumulatedScores = new HashMap<>();

        for (List<PlayerTrainingDTO> playerList : trainingHistory.values()){
            for(PlayerTrainingDTO player : playerList){
                accumulatedScores.put(player.getName(), 
                accumulatedScores.getOrDefault(player.getName(), 0.0) + player.calculateScore())
            } 
        }
    }
}
