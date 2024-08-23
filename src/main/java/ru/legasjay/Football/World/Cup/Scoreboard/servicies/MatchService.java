package ru.legasjay.Football.World.Cup.Scoreboard.servicies;


import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.legasjay.Football.World.Cup.Scoreboard.dto.MatchDTO;
import ru.legasjay.Football.World.Cup.Scoreboard.models.Match;
import ru.legasjay.Football.World.Cup.Scoreboard.repositories.MatchRepository;
import ru.legasjay.Football.World.Cup.Scoreboard.repositories.TeamRepository;
import ru.legasjay.Football.World.Cup.Scoreboard.utils.TeamNotFoundException;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class MatchService {
    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Match> getCurrentMatches() {
        return matchRepository.findCurrentMatchesOrderedByScore();
    }

    public List<Match> getFinishedMatches() {
        return matchRepository.findFinishedMatchesOrderedByScore();
    }

    public void saveMatch(Match match) {
        matchRepository.save(match);
    }

    // Другие методы для добавления команд и редактирования матчей
    public void addMatch(MatchDTO matchDTO) {
        Match match = convertMatchDTOToMatch(matchDTO);
        enrichMatch(match);
        matchRepository.save(match);

//        match.setHomeTeam(teamRepository.findById(homeTeam).orElseThrow(()->new TeamNotFoundException("team not found")));
//        match.setAwayTeam(teamRepository.findById(awayTeam).orElseThrow(()->new TeamNotFoundException("team not found")));
//        match.setHomeScore(homeScore);
//        match.setAwayScore(awayScore);
//        match.setMatchOver(false);
//        match.setStartTime(System.currentTimeMillis());
//        match.setShowHomeAway(isShowHomeAway);

    }

    public void updateMatch(int id, int homeScore, int awayScore) {
        Match match = matchRepository.findById(id).orElseThrow(() -> new RuntimeException("Матч не найден"));
        match.setHomeScore(homeScore);
        match.setAwayScore(awayScore);
//        match.setMatchOver(homeScore >= 0 && awayScore >= 0); // Можно дополнить условия завершения
        matchRepository.save(match);
    }

    @Transactional
    public void deleteMatch(int id) {
        matchRepository.deleteById(id);
    }

    public MatchDTO createMatchDTO(Match match) {
        MatchDTO dto = new MatchDTO();
        dto.setMatchId(match.getMatchId());
        dto.setHomeTeam(match.getHomeTeam());
        dto.setAwayTeam(match.getAwayTeam());
        dto.setHomeScore(match.getHomeScore());
        dto.setAwayScore(match.getAwayScore());

        // Рассчитываем текущее время матча
//        long elapsedSeconds = Duration.between(match.getStartTime(), LocalDateTime.now()).getSeconds();
        dto.setMatchTime(formatDuration(match.getStartTime()));

        return dto;
    }

    private String formatDuration(long startTime) {

        long currentTime = System.currentTimeMillis();
//        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(currentTime - startTime);

        long minutes = seconds / 60;
        seconds = seconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public MatchDTO convertMatchToMatchDTO(Match match) {
        return modelMapper.map(match, MatchDTO.class);
    }

    public Match convertMatchDTOToMatch(MatchDTO matchDTO) {
        return modelMapper.map(matchDTO, Match.class);
    }

    public void enrichMatch(Match match) {
        match.setStartTime(System.currentTimeMillis());
    }
}

