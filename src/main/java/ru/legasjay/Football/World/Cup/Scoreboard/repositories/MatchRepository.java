package ru.legasjay.Football.World.Cup.Scoreboard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.legasjay.Football.World.Cup.Scoreboard.models.Match;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Integer> {

    @Query("SELECT m FROM Match m WHERE m.matchOver = false ORDER BY (m.homeScore + m.awayScore) DESC, m.startTime DESC")
    List<Match> findCurrentMatchesOrderedByScore();

    @Query("SELECT m FROM Match m WHERE m.matchOver = true ORDER BY (m.homeScore + m.awayScore) DESC, m.startTime DESC")
    List<Match> findFinishedMatchesOrderedByScore();

}
