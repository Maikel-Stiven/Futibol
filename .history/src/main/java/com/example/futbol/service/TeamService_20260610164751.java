package com.example.futbol.service;

import com.example.futbol.dto.PlayerTrainingDTO;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class TeamService {
    
    private final Map<Integer, List<PlayerTrainingDTO>> trainingHistory = new ConcurrentHashMap<>(); 
}
