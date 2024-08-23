package ru.legasjay.Football.World.Cup.Scoreboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.legasjay.Football.World.Cup.Scoreboard.servicies.TeamService;

@Controller
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping("/add")
    public String addTeam(@RequestParam String teamName) {
        teamService.addTeam(teamName);
        return "redirect:/matches/admin";
    }

    // Другие методы для редактирования и удаления команд
}
