package org.example.demooooooo.Controller;

import org.example.demooooooo.Service.Detail;
import org.example.demooooooo.Service.signupserivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("SIGNUP1")
public class Signupcontroller
{
    @GetMapping
    public String SIGNUP1()
    {
        return "SIGNUP1";
    }
 private final signupserivce signupserivce;
 public Signupcontroller(signupserivce signupserivce)
 {
     this.signupserivce=signupserivce;
 }
 @PostMapping
    public String signup(@RequestParam String name,@RequestParam String password,@RequestParam String email)
    {
        Detail detail=new Detail();
        detail.setEmail(email);
        detail.setName(name);
        detail.setPassword(password);
        signupserivce.signup(detail);
        return "redirect:/LOGIN";
    }



}
