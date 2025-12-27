package org.example.demooooooo.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("expense")
public class addexpensecontroller {
    @GetMapping
    public String addexpensecontroller(Model model)
        {
        return "addexpensecontroller";
        }


//        @PostMapping
//        public String addexpenses1(@RequestParam String )

}
