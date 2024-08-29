package ru.legasjay.Football.World.Cup.Scoreboard.utils;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import ru.legasjay.Football.World.Cup.Scoreboard.dto.MatchDTO;
import ru.legasjay.Football.World.Cup.Scoreboard.models.Match;
import ru.legasjay.Football.World.Cup.Scoreboard.servicies.MatchService;

import java.util.concurrent.TimeUnit;

@Mapper(componentModel = "spring")
public abstract class MatchMapper {

    @Autowired
    protected TimeFormatService timeFormatService;

    @Mapping(target = "matchTime", expression = "java(timeFormatService.formatDuration(match.getStartTime()))")
    @Mapping(target = "totalScore", expression = "java(match.getTotalGoals())")
    public abstract MatchDTO matchToMatchDTO(Match match);

    public abstract Match matchDTOToMatch(MatchDTO matchDTO);
}
