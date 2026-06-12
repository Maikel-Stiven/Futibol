package com.example.futbol.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingRequestDTO {
    
    @JsonProperty("numeroEntrenamiento")
    private int trainingNumber;

    @JsonProperty("jugadores")
    private List<PlayerTrainingDTO> players;
}
