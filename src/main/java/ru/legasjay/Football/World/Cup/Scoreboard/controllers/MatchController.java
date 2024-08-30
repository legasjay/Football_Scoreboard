package ru.legasjay.Football.World.Cup.Scoreboard.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.legasjay.Football.World.Cup.Scoreboard.dto.CreateMatchDTO;
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

    @GetMapping("/scoreboard")
    public String getScoreboard(Model model) {
        model.addAttribute("current_matches",
                matchService.getAllMatchesAsDTO(matchService.getCurrentMatches()));
        model.addAttribute("finished_matches",
                matchService.getAllMatchesAsDTO(matchService.getFinishedMatches()));
        return "scoreboard";
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model) {
        try {
            model.addAttribute("matches",
                    matchService.getAllMatchesAsDTO(matchService.getCurrentMatches()));
            List<Team> teams = teamService.getAllTeams();
            model.addAttribute("teams", teams);
            model.addAttribute("newMatchDTO", new MatchDTO());
            return "score_board_admin";
        } catch (Exception e) {
            return e.toString();
        }

    }

    @PostMapping("/add")
    public String addMatch(@ModelAttribute CreateMatchDTO newMatchDTO) {
        matchService.addMatch(newMatchDTO);
        return "redirect:/matches/admin";
    }

    @PostMapping("/update")
    public String updateMatch(@ModelAttribute MatchDTO match) {
        matchService.updateMatch(match);
        return "redirect:/matches/admin";
    }

    @PostMapping("/delete")
    public String deleteMatch(@RequestParam int id) {
        matchService.deleteMatch(id);
        return "redirect:/matches/admin";
    }

}
