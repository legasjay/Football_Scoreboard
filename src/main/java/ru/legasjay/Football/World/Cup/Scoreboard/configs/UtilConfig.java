package ru.legasjay.Football.World.Cup.Scoreboard.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
