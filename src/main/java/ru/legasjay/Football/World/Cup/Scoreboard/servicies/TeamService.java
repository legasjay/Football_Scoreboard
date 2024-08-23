package ru.legasjay.Football.World.Cup.Scoreboard.servicies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.legasjay.Football.World.Cup.Scoreboard.models.Team;
import ru.legasjay.Football.World.Cup.Scoreboard.repositories.TeamRepository;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public void addTeam(Team team) {
        teamRepository.save(team);
    }

    public void addTeam(String teamName) {
        Team team = new Team();
        team.setTeamName(teamName);
        teamRepository.save(team);
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Optional<Team> findById(Integer id) {
        return teamRepository.findById(id);
    }

}
