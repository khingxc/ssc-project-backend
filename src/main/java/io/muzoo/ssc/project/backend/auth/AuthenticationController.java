package io.muzoo.ssc.project.backend.auth;

import io.muzoo.ssc.project.backend.SimpleResponseDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthenticationController {

    @GetMapping("/api/test")
    public String test() {
        return "if this message is shown, it means login is successful cuz we didn't set to permit this path.";
    }

    @PostMapping("/api/login")
    public SimpleResponseDTO login(HttpServletRequest req) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            // logging in twice hash an error
            Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principle != null && principle instanceof org.springframework.security.core.userdetails.User) {
                req.logout();
            }
            req.login(username, password);
            return SimpleResponseDTO
                    .builder()
                    .success(true)
                    .message("successfully log in!")
                    .build();
        } catch (ServletException e) {
            return SimpleResponseDTO
                    .builder()
                    .success(false)
                    .message(e.getMessage())
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
                    .message(e.getMessage())
                    .build();
        }
    }

}
