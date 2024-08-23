package ru.legasjay.Football.World.Cup.Scoreboard.dto;

import lombok.Data;
import ru.legasjay.Football.World.Cup.Scoreboard.models.Team;

@Data
public class MatchDTO {

    private Long id;
    private Team homeTeam;
    private Team awayTeam;
    private int homeScore;
    private int awayScore;
    private String matchTime;

}
