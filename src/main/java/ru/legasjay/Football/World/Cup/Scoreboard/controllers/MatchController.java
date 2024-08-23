package ru.legasjay.Football.World.Cup.Scoreboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        model.addAttribute("current_matches", matchService.getCurrentMatches());
        model.addAttribute("finished_matches", matchService.getFinishedMatches());
        return "scoreboard";
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model) {
        model.addAttribute("matches", matchService.getCurrentMatches());
        List<Team> teams = teamService.getAllTeams();
        model.addAttribute("teams", teams);
        // Добавить команды и др. атрибуты для админской страницы
        return "score_board_admin";
    }

    // Метод для добавления и редактирования матчей


    @PostMapping("/add")
    public String addMatch(@RequestParam Integer homeTeam,
                           @RequestParam Integer awayTeam,
                           @RequestParam int homeScore,
                           @RequestParam int awayScore,
                           @RequestParam(required = false, defaultValue = "false") boolean isShowHomeAway ) {
        matchService.addMatch(homeTeam, awayTeam, homeScore, awayScore, isShowHomeAway);
        return "redirect:/matches/admin"; // Предполагая, что это URL для админской страницы
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

    // Здесь также добавьте методы для управления командами
}
