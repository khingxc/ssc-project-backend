package io.muzoo.ssc.project.backend.auth;

import io.muzoo.ssc.project.backend.SimpleResponseDTO;
import io.muzoo.ssc.project.backend.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


@RestController
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/api/test")
    public String test() {
        return "if this message is shown, it means login is successful cuz we didn't set to permit this path.";
    }

    @PostMapping("/api/login")
    public SimpleResponseDTO login(HttpServletRequest req) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
//             Check if there is a current user logged in, if so log out first
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal != null && principal instanceof User) {
                req.logout();
            }
            req.login(email, password);
            return SimpleResponseDTO
                    .builder()
                    .success(true)
                    .message("successfully log in!")
                    .build();
        } catch (ServletException e) {
            return SimpleResponseDTO
                    .builder()
                    .success(false)
                    .message("incorrect email or password, failed to log in!")
                    .build();
        }
    }

    @GetMapping("/api/logout")
    public SimpleResponseDTO logout(HttpServletRequest req) {
        try {
            req.logout();
            return SimpleResponseDTO
                    .builder()
                    .success(true)
                    .message("successfully log out!")
                    .build();
        } catch (ServletException e) {
            return SimpleResponseDTO
                    .builder()
                    .success(false)
                    .message("failed to log out!")
                    .build();
        }
    }

    @PostMapping("/api/signup")
    public SimpleResponseDTO signup(HttpServletRequest req) throws UserServiceException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String displayName = req.getParameter("display_name");
        try {
            io.muzoo.ssc.project.backend.auth.User newUser = userRepository.findFirstByEmail(email);
            if (newUser == null) {
                newUser = new io.muzoo.ssc.project.backend.auth.User();
                newUser.setEmail(email);
                newUser.setPassword(passwordEncoder.encode(password));
                newUser.setDisplayName(displayName);
                newUser.setRole("USER");
                userRepository.save(newUser);
                return SimpleResponseDTO.builder()
                        .success(true)
                        .message("successfully created new user")
                        .build();
            }
            else {
                throw new EmailAlreadyUsedException(String.format("%s has already been used", email));
            }
        } catch (UserServiceException e){
            return SimpleResponseDTO
                    .builder()
                    .success(false)
                    .message(String.format("%s has already been used", email))
                    .build();
        }
    }

    @PostMapping("/api/change_pass")
    public SimpleResponseDTO changePass(HttpServletRequest req){
        String email = req.getParameter("email");
        String newPassword = req.getParameter("password");

        io.muzoo.ssc.project.backend.auth.User user = userRepository.findFirstByEmail(email);

        user.setPassword(newPassword);
        userRepository.save(user);
        return SimpleResponseDTO.builder()
                .success(true)
                .message("successfully changed password")
                .build();
    }
}
