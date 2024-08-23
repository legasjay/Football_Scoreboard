package ru.legasjay.Football.World.Cup.Scoreboard.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "match")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchId;


    @ManyToOne
    @JoinColumn(name = "home_team_id", nullable = false)
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id", nullable = false)
    private Team awayTeam;

    private int homeScore;
    private int awayScore;
    private boolean isShowHomeAway;
    private long startTime;
    private boolean isMatchOver;

    // дополнительные методы, если нужно
    public int getTotalGoals() {
        return homeScore + awayScore;
    }
}
