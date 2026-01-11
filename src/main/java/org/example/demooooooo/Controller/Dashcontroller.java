package org.example.demooooooo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/DASHBOARD")
public class Dashcontroller
{
    @GetMapping
    public String Dashboard()
    {
        return "DASHBOARD";
    }
}
