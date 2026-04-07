package org.example.demooooooo.Controller;

import org.example.demooooooo.Entity.users1;
import org.example.demooooooo.Service.UsersService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsersLogin {

    private final UsersService userService;

    public UsersLogin(UsersService userService)

    {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showLogin() {
        return "Users";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session) {

        users1 user = userService.login(username, password);

        if (user != null) {
            session.setAttribute("user", user); // ✅ FIXED
            return "redirect:/trackerdash";     // ✅ FIXED
        }

        return "Users";
    }
}