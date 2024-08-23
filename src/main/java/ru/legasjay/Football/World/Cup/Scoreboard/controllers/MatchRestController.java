package ru.legasjay.Football.World.Cup.Scoreboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.legasjay.Football.World.Cup.Scoreboard.models.Match;
import ru.legasjay.Football.World.Cup.Scoreboard.models.Team;
import ru.legasjay.Football.World.Cup.Scoreboard.servicies.MatchService;
import ru.legasjay.Football.World.Cup.Scoreboard.servicies.TeamService;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class MatchRestController {
    @Autowired
    private MatchService matchService;

    @Autowired
    private TeamService teamService;

    @GetMapping
    public List<Match> getMatches() {
        return matchService.getCurrentMatches();
    }

    @PostMapping("/add-match")
    public ResponseEntity<?> addMatch(@RequestBody Match match) {
        matchService.saveMatch(match);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update-match")
    public ResponseEntity<?> updateMatch(@RequestBody Match match) {
        matchService.saveMatch(match);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add-team")
    public ResponseEntity<?> addTeam(@RequestBody Team team) {
        teamService.addTeam(team);
        return ResponseEntity.ok().build();
    }
}
