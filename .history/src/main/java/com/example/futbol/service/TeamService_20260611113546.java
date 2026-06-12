package com.example.futbol.service;

import com.example.futbol.dto.PlayerResonseDTO;
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

    public boolean saveTraining(int trainingNumber, List<PlayerTrainingDTO> players){
        if (players ==  null || players.isEmpty()){
            return false;
        }
        this.trainingHistory.put(trainingNumber, players);
        return true;
    }
    

    public List<PlayerResponseDTO> getStarting(){
        if (this.trainingHistory.size() < Required_trainings){
            throw new IllegalStateException("No hay suficiente información");
        }

        Map<String, Double> accumulatedScores = new HashMap<>();

        for (List<PlayerTrainingDTO> playerList : trainingHistory.values()){
            for(PlayerTrainingDTO player : playerList){
                accumulatedScores.put(player.getName(), 
                accumulatedScores.getOrDefault(player.getName(), 0.0) + player.calculateScore());
            } 
        }

        return accumulatedScores.entrySet().stream().map(entry -> {
            Map<String, Object> playerResult = new LinkedHashMap<>();
            playerResult.put("nombre", entry.getKey());

            double average = Math.round((entry.getValue() / Required_trainings) * 10.0) / 10.0;
            playerResult.put("Promedio semanal", average);
            return playerResult;
        })

        .sorted((a, b) -> Double.compare((double) b.get("Promedio semanal"), (double) a.get ("Promedio semanal")))
        .limit(Starting_Players_Count)
        .collect(Collectors.toList());
    
    }
    
}
