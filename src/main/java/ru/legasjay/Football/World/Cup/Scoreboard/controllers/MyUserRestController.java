package ru.legasjay.Football.World.Cup.Scoreboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.legasjay.Football.World.Cup.Scoreboard.models.MyUser;
import ru.legasjay.Football.World.Cup.Scoreboard.servicies.MyUserDetailsService;

@RestController
public class MyUserRestController {

    private final MyUserDetailsService myUserDetailsService;

    @Autowired
    public MyUserRestController(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @PostMapping("/add_user")
    public String addUser(@RequestBody MyUser myUser, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            myUserDetailsService.saveUser(myUser);
            return "user saved";
        } else {
            return "not saved";
        }
    }

}
