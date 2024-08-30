package ru.legasjay.Football.World.Cup.Scoreboard.servicies;


import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.legasjay.Football.World.Cup.Scoreboard.dto.CreateMatchDTO;
import ru.legasjay.Football.World.Cup.Scoreboard.dto.MatchDTO;
import ru.legasjay.Football.World.Cup.Scoreboard.models.Match;
import ru.legasjay.Football.World.Cup.Scoreboard.models.Team;
import ru.legasjay.Football.World.Cup.Scoreboard.repositories.MatchRepository;
import ru.legasjay.Football.World.Cup.Scoreboard.repositories.TeamRepository;
import ru.legasjay.Football.World.Cup.Scoreboard.utils.CreateMatchMapper;
import ru.legasjay.Football.World.Cup.Scoreboard.utils.MatchMapper;
import ru.legasjay.Football.World.Cup.Scoreboard.utils.TeamNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class MatchService {
    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MatchMapper matchMapper;

    @Autowired
    private CreateMatchMapper createMatchMapper;

    @Autowired
    private TeamService teamService;

    public List<Match> getCurrentMatches() {
        return matchRepository.findCurrentMatchesOrderedByScore();
    }

    public List<Match> getFinishedMatches() {
        return matchRepository.findFinishedMatchesOrderedByScore();
    }

    public void saveMatch(Match match) {
        matchRepository.save(match);
    }

    public void addMatch(CreateMatchDTO createMatchDTO) {
        Team homeTeam = teamService.findById(createMatchDTO.getHomeTeamId()).orElseThrow(() -> new TeamNotFoundException("not found"));
        Team awayTeam = teamService.findById(createMatchDTO.getAwayTeamId()).orElseThrow(() -> new TeamNotFoundException("not found"));

        Match match = createMatchMapper.createMatchDtoToMatch(createMatchDTO);

        match.setHomeTeam(homeTeam);
        match.setAwayTeam(awayTeam);

        enrichMatch(match);
        matchRepository.save(match);
    }

    public void updateMatch(MatchDTO matchDTO) {
        Match match = matchRepository.findById(matchDTO.getMatchId()).orElseThrow(() ->
                new RuntimeException("Матч не найден"));
        match.setHomeScore(matchDTO.getHomeScore());
        match.setAwayScore(matchDTO.getAwayScore());
        matchRepository.save(match);
    }

    @Transactional
    public void deleteMatch(int id) {
        matchRepository.deleteById(id);
    }

    private String formatDuration(long startTime) {

        long currentTime = System.currentTimeMillis();
        long seconds = TimeUnit.MILLISECONDS.toSeconds(currentTime - startTime);

        long minutes = seconds / 60;
        seconds = seconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public void enrichMatch(Match match) {
        match.setStartTime(System.currentTimeMillis());
    }

    public List<MatchDTO> getAllMatchesAsDTO(List<Match> matches) {
        return matches.stream()
                .map(matchMapper::matchToMatchDTO)
                .collect(Collectors.toList());
    }

}

