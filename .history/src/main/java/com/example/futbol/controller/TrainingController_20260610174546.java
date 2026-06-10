package com.example.futbol.controller;

import com.example.futbol.dto.PlayerTrainingDTO;
import com.example.futbol.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/Entrenamientos")
public class TrainingController {

    @Autowired
    private TeamService teamService;

    @PostMapping
    public ResponseEntity<Map<String, String>> registerTraining(@RequestBody Map<String, Object> payload){
        
        int number = (int) payload.get("Numeros de entrenamientos");

        List<Map<String, Object>> playersPayload = (List<Map<String, Object>>) payload.get("Jugadores");
        List<PlayerTrainingDTO> players = new ArrayList<>();

        if (playersPayload != null) {
            for (Map<String, Object> playerData : playersPayload) {
                PlayerTrainingDTO player = new PlayerTrainingDTO();
                for (Map.Entry<String, Object> entry : playerData.entrySet()) {
                    try {
                        PropertyDescriptor pd = new PropertyDescriptor(entry.getKey(), PlayerTrainingDTO.class);
                        if (pd.getWriteMethod() != null) {
                            pd.getWriteMethod().invoke(player, entry.getValue());
                        }
                    } catch (Exception ignored) {
                    }
                }
                players.add(player);
            }
        }

        teamService.saveTraining(number, players);

        Map<String, String> response = new HashMap<>();
        response.put("Mensaje", "# Entrenamientos" + number + "registrados de manera éxitosa");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/Titulares")
    public ResponseEntity<?> getStarting(){
        try{
            List<Map<String, Object>> lineup =teamService.getStarting();
            return ResponseEntity.ok(lineup);
        }catch(IllegalStateException exception){
            Map<String, String> error = new HashMap<>();
            error.put("Mensaje", exception.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        } 
    }
    
}
