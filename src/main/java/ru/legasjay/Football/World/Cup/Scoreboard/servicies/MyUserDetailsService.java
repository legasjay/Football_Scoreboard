package ru.legasjay.Football.World.Cup.Scoreboard.servicies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.legasjay.Football.World.Cup.Scoreboard.models.MyUser;
import ru.legasjay.Football.World.Cup.Scoreboard.repositories.MyUserRepository;
import ru.legasjay.Football.World.Cup.Scoreboard.security.MyUserDetails;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private  MyUserRepository myUserRepository;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> myUser = myUserRepository.findByUsername(username);
        return myUser.map(MyUserDetails::new).orElseThrow();
    }

    public void saveUser(MyUser myUser) {
        String passwordEncode = passwordEncoder.encode(myUser.getPassword());
        myUser.setPassword(passwordEncode);
        myUserRepository.save(myUser);
    }

}
