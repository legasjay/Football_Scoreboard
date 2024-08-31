package ru.legasjay.Football.World.Cup.Scoreboard.utils;

public class MatchNotFoundException extends RuntimeException {
    public MatchNotFoundException(String message) {
        super(message);
    }
}
