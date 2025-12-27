package org.example.demooooooo.Controller;

import org.example.demooooooo.Service.Detail;
import org.example.demooooooo.Service.loginserivce;
import org.example.demooooooo.Service.signupserivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/LOGIN")
public class logincontroller
{
    private final loginserivce lserivce;

    public logincontroller(loginserivce lserivce) {
        this.lserivce = lserivce;
    }

    @GetMapping
  public String LOGIN()
  {
      return "LOGIN";
  }
  @PostMapping
    public String LOGINPOST(@RequestParam String email, @RequestParam String password, Model model)
  {
     Detail user=lserivce.authenticate(email,password) ;
      if (user == null) {
          model.addAttribute("error", "Invalid email or password");
          return "LOGIN";
      }
      return  "redirect:/DASHBOARD";
  }

}
