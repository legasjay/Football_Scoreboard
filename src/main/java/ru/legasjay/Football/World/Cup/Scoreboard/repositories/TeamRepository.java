package ru.legasjay.Football.World.Cup.Scoreboard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.legasjay.Football.World.Cup.Scoreboard.models.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {
}
