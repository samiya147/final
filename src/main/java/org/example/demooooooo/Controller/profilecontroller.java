package org.example.demooooooo.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.demooooooo.Entity.users1;
import org.example.demooooooo.Service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class profilecontroller {

    private final UsersService usersService;

    public profilecontroller(UsersService usersService) {
        this.usersService = usersService;
    }

    // ================= SHOW PROFILE =================
    @GetMapping("/profile")
    public String showProfile(HttpSession session, Model model) {

        users1 user = (users1) session.getAttribute("user"); // ✅ FIXED

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
        return "profile";
    }

    // ================= SAVE PROFILE =================
    @PostMapping("/profile/save")
    public String saveProfile(@ModelAttribute users1 formUser,
                              HttpSession session,
                              Model model) {

        users1 sessionUser = (users1) session.getAttribute("user"); // ✅ FIXED

        if (sessionUser == null) {
            return "redirect:/";
        }

        // keep same ID (update)
        formUser.setId(sessionUser.getId());
        formUser.setUsername(sessionUser.getUsername());
        formUser.setPassword(sessionUser.getPassword());

        users1 savedUser = usersService.save(formUser);

        // update session
        session.setAttribute("user", savedUser); // ✅ FIXED

        model.addAttribute("user", savedUser);
        model.addAttribute("success", "Profile saved successfully!");

        return "trackerdash";
    }
}