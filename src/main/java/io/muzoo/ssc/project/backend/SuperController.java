package io.muzoo.ssc.project.backend;

import io.muzoo.ssc.project.backend.auth.User;
import io.muzoo.ssc.project.backend.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

public class SuperController {

    @Autowired
    private UserRepository userRepository;

    public User getCurrentUser(){
        User u = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof org.springframework.security.core.userdetails.User) {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) principal;
            u = userRepository.findFirstByEmail(user.getUsername());
        }
        return u;
    }
}
