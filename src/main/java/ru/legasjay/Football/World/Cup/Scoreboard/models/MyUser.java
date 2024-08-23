package ru.legasjay.Football.World.Cup.Scoreboard.models;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "football_users")
public class MyUser {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String username;
    private String password;
    private String roles;
    private String email;

}
