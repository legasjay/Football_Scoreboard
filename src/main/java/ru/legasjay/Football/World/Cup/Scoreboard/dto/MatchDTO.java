package ru.legasjay.Football.World.Cup.Scoreboard.dto;

import lombok.Data;
import ru.legasjay.Football.World.Cup.Scoreboard.models.Team;

@Data
public class MatchDTO {

    private int matchId;
    private Team homeTeam;
    private Team awayTeam;
    private int homeScore;
    private int awayScore;
    private boolean isShowHomeAway = false;
    private boolean isMatchOver = false;

    private String matchTime;
    private int totalScore;

    public int getTotalGoals() {
        return homeScore + awayScore;
    }
}
