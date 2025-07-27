package com.wintaylor.olympicgames.controller;

import com.wintaylor.olympicgames.model.Athlete;
import com.wintaylor.olympicgames.service.AthleteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/athletes")
public class AthleteController {
    private final AthleteService athleteService;

    public AthleteController(AthleteService athleteService) {
        this.athleteService = athleteService;
    }

    @GetMapping
    @Operation(description = "Exercício 1")
    public HashMap<String, Athlete> getAllAthletes() {
        return athleteService.getAll();
    }

    @GetMapping("/{index}")
    public Athlete getAthleteByIndex(@PathVariable String index) {
        return athleteService.getByIndex(index);
    }

    @PatchMapping("/{id}")
    @Operation(description = "Exercício 8")
    public ResponseEntity<String> updateAthlete(@PathVariable String id, @RequestBody Map<String, Object> updates) {
        try {
            athleteService.updateAthlete(id, updates);
            return ResponseEntity.ok("Athlete updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
