package ru.legasjay.Football.World.Cup.Scoreboard.dto;

import lombok.Data;

@Data
public class CreateMatchDTO {
    private int homeTeamId;
    private int awayTeamId;
    private int homeScore;
    private int awayScore;
    private boolean isShowHomeAway; 
}
