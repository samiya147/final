package org.example.demooooooo.Controller;


import org.example.demooooooo.Entity.Detail;
import org.example.demooooooo.Entity.Expense;
import org.example.demooooooo.Entity.groupdetail;
import org.example.demooooooo.Repository.Repository;
import org.example.demooooooo.Repository.grouprepository;
import org.example.demooooooo.Service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/addexpenses")
public class addexpensecontroller
{
    private final addexpenseservice addexpenseservice;
    private final groupservice groupservice;
    private final grouprepository grouprep;
    private final Repository rep;

    public addexpensecontroller(addexpenseservice addexpenseservice, grouprepository grouprep, Repository rep,groupservice groupservice) {
        this.addexpenseservice = addexpenseservice;
        this.grouprep= grouprep;
        this.rep=rep;
        this.groupservice=groupservice;

    }

    @GetMapping("/{group_name}")
    public String showaddexpense(Model model, @PathVariable String group_name)
    {
        groupdetail group=grouprep.findById(group_name).orElseThrow(() -> new RuntimeException("Group not found"));
        List<Detail> members=group.getMembers();
        model.addAttribute("members",members);
        model.addAttribute("group_name",group_name);
        return "addexpenses";
    }



    @PostMapping("/{group_name}")
    public String addexpenses1(@PathVariable String group_name,@RequestParam String description, @RequestParam Double amount,
                               @RequestParam String  email1,@RequestParam List<String> splits)
    {
        Detail pays = rep.findByEmail(email1)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        groupdetail group=grouprep.findById(group_name).orElseThrow(() -> new RuntimeException("Group not found"));
        List<Detail> members = splits.stream()
                .map(email -> rep.findByEmail(email)
                        .orElseThrow(() -> new RuntimeException("Member not found: " + email)))
                .collect(Collectors.toList());
        Expense expense=new Expense();
        expense.setDescription(description);
        expense.setAmount(amount);
        expense.setPay(pays);
        expense.setGroup(group);
        addexpenseservice.addExpense(expense,members);
        return "redirect:/group/{groupName}";
    }
}