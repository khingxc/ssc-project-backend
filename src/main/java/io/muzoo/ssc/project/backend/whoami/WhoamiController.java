package io.muzoo.ssc.project.backend.whoami;

import io.muzoo.ssc.project.backend.User;
import io.muzoo.ssc.project.backend.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.web.servlet.oauth2.login.UserInfoEndpointDsl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A controler to retrieve currently logged-in user
 */
@RestController
public class WhoamiController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Make sure that all API path begins with /api. This will be useful when we do proxy.
     */
    @GetMapping("/api/whoami")
    public WhoamiDTO whoami() {
        // Put try around the statement becuase we use nested dot notation which could raise a NullPointException
        try{
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal != null && principal instanceof org.springframework.security.core.userdetails.User){
                org.springframework.security.core.userdetails.User user = ( org.springframework.security.core.userdetails.User) principal;
                User u = userRepository.findFirstByUsername(user.getUsername());
                return WhoamiDTO.builder()
                        .loggedIn(true)
                        .name(u.getUsername())
                        .role(u.getRole())
                        .username(u.getUsername())
                        .build();
            }
        }
        catch (Exception e){

        }

        return WhoamiDTO.builder()
                .loggedIn(false)
                .build();

    }

}
