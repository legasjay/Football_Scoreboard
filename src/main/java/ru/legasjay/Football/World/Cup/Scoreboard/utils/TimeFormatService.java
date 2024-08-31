package ru.legasjay.Football.World.Cup.Scoreboard.utils;


import org.springframework.stereotype.Service;


import java.util.concurrent.TimeUnit;

@Service
public class TimeFormatService {

    public String formatDuration(long startTime) {
        long currentTime = System.currentTimeMillis();
        long seconds = TimeUnit.MILLISECONDS.toSeconds(currentTime - startTime);
        long minutes = seconds / 60;
        seconds = seconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

}
