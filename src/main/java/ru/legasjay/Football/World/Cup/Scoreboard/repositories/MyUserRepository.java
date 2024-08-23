package ru.legasjay.Football.World.Cup.Scoreboard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.legasjay.Football.World.Cup.Scoreboard.models.MyUser;

import java.util.Optional;


public interface MyUserRepository extends JpaRepository<MyUser, Integer> {

    public Optional<MyUser> findByUsername(String username);

}
