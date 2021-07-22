package io.muzoo.ssc.project.backend.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OurUserDetailsServices implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        io.muzoo.ssc.project.backend.auth.User u = userRepository.findFirstByEmail(email);
        if (u != null) {
            return User.withUsername(u.getEmail())
                    .password(u.getPassword())
                    .roles(u.getRole())
                    .build();
        }
        else{
            throw new UsernameNotFoundException("Invalid Username/Password!!!");
        }
    }

}
