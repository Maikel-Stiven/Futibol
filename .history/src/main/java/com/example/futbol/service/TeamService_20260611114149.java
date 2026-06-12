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
                double dailyScore = (player.getShotPower()*20)
                                    + (player.getSpeed() * 30)
                                    + (player.getEffectivePasses() * 50);

                accumulatedScores.put(player.getName(),
                accumulatedScores.getOrDefault(player.getName(), 0.0) + dailyScore
            );                         
        }
    }

        return accumulatedScores.entrySet().stream().map(entry -> {
            double average = Math.round((entry.getValue() / Required_trainings))
        })

        .sorted((a, b) -> Double.compare((double) b.get("Promedio semanal"), (double) a.get ("Promedio semanal")))
        .limit(Starting_Players_Count)
        .collect(Collectors.toList());
    
    }
    
}
