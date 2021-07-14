package io.muzoo.ssc.project.backend.whoami;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.muzoo.ssc.project.backend.UserRepository;
import io.muzoo.ssc.project.backend.User;

/**
 * A controller to retrieve current logged-in user
 */
@RestController
public class WhoamiController {

    @Autowired
    private UserRepository userRepository;


    /**
     * Make sure that all API paths begin with /api
     */
    @GetMapping("/api/whoami")
    public WhoamiDTO whoami(){
//        put try around the statement because we use nested dot notation which could raise a NullPointerExceptio
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal != null && principal instanceof org.springframework.security.core.userdetails.User){
//                user is logged in
                org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) principal;
                User u = userRepository.findFirstByUsername(user.getUsername());

                return WhoamiDTO.builder()
                        .loggedin(true)
                        .name(u.getUsername()) // we can add field "name" later (name is in frontend part)
                        .role(u.getRole())
                        .username(u.getUsername())
                        .build();
            }
        } catch (Exception ignored){
        }
//                user is not logged in
        return WhoamiDTO.builder()
                .loggedin(false)
                .build();
    }
}
