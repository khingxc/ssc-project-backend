package io.muzoo.ssc.project.backend.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class AuthenticationController {

    @GetMapping("/api/test")
    public String test() {
        return "if this message is shown, it means login is successful cuz we didn't set to permit this path.";
    }

    @PostMapping("/api/login")
    public String login(HttpServletRequest req) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            req.login(username, password);
            return "login successful";
        } catch (ServletException e) {
            return "failed to login";
        }

    }

    @GetMapping("/api/logout")
    public String logout(HttpServletRequest req) {
        try {
            req.logout();
            return "logout successful";
        } catch (ServletException e) {
            return "failed to logout";
        }
    }

}
