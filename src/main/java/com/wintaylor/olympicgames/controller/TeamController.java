package com.wintaylor.olympicgames.controller;

import com.wintaylor.olympicgames.dto.AthleteFilterRequest;
import com.wintaylor.olympicgames.dto.AthleteRequest;
import com.wintaylor.olympicgames.model.Athlete;
import com.wintaylor.olympicgames.service.AthleteService;
import com.wintaylor.olympicgames.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/team")
public class TeamController {
    private final TeamService teamService;
    private final AthleteService athleteService;

    public TeamController(TeamService teamService, AthleteService athleteService) {
        this.athleteService = athleteService;
        this.teamService = teamService;
    }

    @PostMapping("/add")
    @Operation(description = "Exercício 6")
    public ResponseEntity<String> addAthleteToTeam(@Valid @RequestBody AthleteRequest request) {
        try {
            teamService.addAthleteToTeamBrazilMaleFootball(request);
            return ResponseEntity.ok("Athlet added to team successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/filter")
    @Operation(description = "Exercício 7")
    public ResponseEntity<?> filterTeamAthletes(
            @Valid AthleteFilterRequest filter,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
        }

        List<Athlete> athletes = teamService.filterAthletes(filter);
        return ResponseEntity.ok(athletes);
    }

    @GetMapping("/paris/preselection")
    @Operation(description = "Exercício 9")
    public ResponseEntity<Map<String, Athlete>> getPreSelectionForParis() {
        Map<String, Athlete> preSelected = athleteService.getPreSelectedFemaleAthletesUnder20ExcludingFootball();
        return ResponseEntity.ok(preSelected);
    }

    @PostMapping("/paris/preselection/add-and-list")
    @Operation(description = "Exercício 10")
    public ResponseEntity<Map<String, Athlete>> addAndPreselectParisAthletes(@Valid @RequestBody AthleteRequest request) {
        Map<String, Athlete> result = teamService.addFemaleAthleticBrazilAndPreselectParisAthletes(request);
        return ResponseEntity.ok(result);
    }

}
