package com.example.futbol.controller;

import com.example.futbol.dto.PlayerTrainingDTO;
import com.example.futbol.dto.TrainingRequestDTO;
import com.example.futbol.dto.PlayerResponseDTO;
import com.example.futbol.dto.MessageResponseDTO;
import com.example.futbol.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/Entrenamientos")
public class TrainingController {

    @Autowired
    private TeamService teamService;

    @PostMapping
    public ResponseEntity<MessageResponseDTO> registerTraining(@RequestBody TrainingRequestDTO payload){
        boolean saved = teamService.saveTraining(payload.getTrainingNumber(), payload getPlayers());

        if (!saved) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MessageResponseDTO("La lista de jugadores esta vacia "));
        }

        return ResponseEntity.ok(new MessageResponseDTO("" + payload.getTrainingNumber() + ""));
    }
    
}
