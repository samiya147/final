package org.example.demooooooo.Controller;

import org.example.demooooooo.Repository.Repository;
import org.example.demooooooo.Repository.grouprepository;
import org.example.demooooooo.Service.groupservice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/creategroup")
public class groupcontroller {
    private final Repository repository;
    private final grouprepository grouprepository1;
    private final groupservice groupservice;
    public groupcontroller(Repository repository, grouprepository grouprepository1, groupservice groupservice)
    {
        this.repository = repository;
        this.grouprepository1=grouprepository1;
        this.groupservice=groupservice;
    }


@GetMapping
    public String creategroup( Model model)
{
    model.addAttribute("detail",repository.findAll());
    return "creategroup";
}
@PostMapping
public String addmembers(@RequestParam String group_name,@RequestParam List<String> members )
{
    groupservice.creategroup(group_name,members);
    return "redirect:/DASHBOARD";
}
}
