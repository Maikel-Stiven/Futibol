package com.example.futbol.controller;

import com.example.futbol.dto.PlayerTrainingDTO;
import com.example.futbol.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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

        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        List<PlayerTrainingDTO> players = mapper.convertValue(
            payload.get("Jugadores"),
            new com.fasterxml.jackson.databind.TypeReference<List<PlayerTrainingDTO>>(){}
        )

    }
    
}
