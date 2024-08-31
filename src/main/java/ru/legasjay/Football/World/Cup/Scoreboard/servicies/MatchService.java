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
        Team homeTeam = teamService.findById(createMatchDTO.getHomeTeamId()).orElseThrow(() ->
                new TeamNotFoundException("Команда с ID " + createMatchDTO.getHomeTeamId() + " не найдена"));
        Team awayTeam = teamService.findById(createMatchDTO.getAwayTeamId()).orElseThrow(() ->
                new TeamNotFoundException("Команда с ID " + createMatchDTO.getAwayTeamId() + " не найдена"));

        Match match = createMatchMapper.createMatchDtoToMatch(createMatchDTO);

        match.setHomeTeam(homeTeam);
        match.setAwayTeam(awayTeam);

        enrichMatch(match);
        matchRepository.save(match);
    }

    public void updateMatch(int matchId, int homeScore, int awayScore, boolean matchOver) {
        Match match = matchRepository.findById(matchId).orElseThrow(() ->
                new RuntimeException("Матч с ID " + matchId + " не найден"));
        match.setMatchOver(matchOver);
        match.setHomeScore(homeScore);
        match.setAwayScore(awayScore);
        matchRepository.save(match);
    }

    @Transactional
    public void deleteMatch(int id) {
        matchRepository.deleteById(id);
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

