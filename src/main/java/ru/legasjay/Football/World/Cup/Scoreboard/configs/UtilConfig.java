package ru.legasjay.Football.World.Cup.Scoreboard.configs;

import org.mapstruct.factory.Mappers;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.legasjay.Football.World.Cup.Scoreboard.servicies.MatchService;
import ru.legasjay.Football.World.Cup.Scoreboard.utils.MatchMapper;

@Configuration
public class UtilConfig {

//    @Bean
//    public ModelMapper modelMapper() {
//        return new ModelMapper();
//    }
//
//    @Bean
//    public MatchMapper matchMapper(MatchService matchService) {
//        MatchMapper mapper = Mappers.getMapper(MatchMapper.class);
//        mapper.(matchService); // установите сервис
//        return mapper;
//    }
}
