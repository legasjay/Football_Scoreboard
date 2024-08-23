package ru.legasjay.Football.World.Cup.Scoreboard.utils;

public class TeamNotFoundException extends RuntimeException{
    public TeamNotFoundException(String message) {
        super(message);
    }
}
