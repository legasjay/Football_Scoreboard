package ru.legasjay.Football.World.Cup.Scoreboard.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.legasjay.Football.World.Cup.Scoreboard.dto.MatchDTO;
import ru.legasjay.Football.World.Cup.Scoreboard.models.Match;
import ru.legasjay.Football.World.Cup.Scoreboard.models.Team;
import ru.legasjay.Football.World.Cup.Scoreboard.servicies.MatchService;
import ru.legasjay.Football.World.Cup.Scoreboard.servicies.TeamService;

import java.util.List;


@Controller
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/scoreboard")
    public String getScoreboard(Model model) {
        model.addAttribute("current_matches", matchService.getCurrentMatches());
        model.addAttribute("finished_matches", matchService.getFinishedMatches());
        return "scoreboard";
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model) {
        model.addAttribute("matches", matchService.getCurrentMatches());
        List<Team> teams = teamService.getAllTeams();
        model.addAttribute("teams", teams);
        model.addAttribute("newMatchDTO", new MatchDTO());
        return "score_board_admin";
    }

    @PostMapping("/add")
    public String addMatch(@ModelAttribute MatchDTO newMatchDTO) {
        matchService.addMatch(newMatchDTO);
        return "redirect:/matches/admin";
    }

    @PostMapping("/update")
    public String updateMatch(@RequestParam int id,
                              @RequestParam int homeScore,
                              @RequestParam int awayScore) {
        matchService.updateMatch(id, homeScore, awayScore);
        return "redirect:/matches/admin";
    }

    @PostMapping("/delete")
    public String deleteMatch(@RequestParam int id) {
        matchService.deleteMatch(id);
        return "redirect:/matches/admin";
    }

    public MatchDTO convertMatchToMatchDTO(Match match) {
        return modelMapper.map(match, MatchDTO.class);
    }

    public Match convertMatchDTOToMatch(MatchDTO matchDTO) {
        return modelMapper.map(matchDTO, Match.class);
    }

}
