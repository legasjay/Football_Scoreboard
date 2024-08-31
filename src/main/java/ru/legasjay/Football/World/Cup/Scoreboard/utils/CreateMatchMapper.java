package ru.legasjay.Football.World.Cup.Scoreboard.utils;

import org.mapstruct.Mapper;
import ru.legasjay.Football.World.Cup.Scoreboard.dto.CreateMatchDTO;
import ru.legasjay.Football.World.Cup.Scoreboard.dto.MatchDTO;
import ru.legasjay.Football.World.Cup.Scoreboard.models.Match;

@Mapper(componentModel = "spring")
public abstract class CreateMatchMapper {

    public abstract Match createMatchDtoToMatch(CreateMatchDTO createMatchDTO);

}
