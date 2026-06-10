package com.example.futbol.controller;

import com.example.futbol.dto.PlayerTrainingDTO;
import com.example.futbol.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import 

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


@RestController
@RequestMapping("/api/Entrenamientos")
public class TrainingController {

    @Autowired
    private TeamService teamService;

    @PostMapping
    public ResponseEntity<Map<String, String>> registerTraining(@RequestBody Map<String, Object> payload){
        
        int number = (int) payload.get("Numero de entrenamientos");

        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        List<PlayerTrainingDTO> players = mapper.convertValue(
            payload.get("Jugadores"),
            new com.fasterxml.jackson.databind.TypeReference<List<PlayerTrainingDTO>>(){}
        );

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
