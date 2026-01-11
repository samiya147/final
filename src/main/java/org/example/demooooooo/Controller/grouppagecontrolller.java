package org.example.demooooooo.Controller;

import org.example.demooooooo.Entity.groupdetail;
import org.example.demooooooo.Service.grouppageserivce;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class grouppagecontrolller {

    private final grouppageserivce groupService;

    public grouppagecontrolller(grouppageserivce groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/group/{groupName}")
    public String viewGroupPage(@PathVariable String groupName, Model model) {

        groupdetail group = groupService.getGroup(groupName);

        model.addAttribute("groupname", group.getGroup_name());
        model.addAttribute("members", groupService.getMembers(groupName));
        model.addAttribute("expenses", groupService.getExpenses(groupName));
        model.addAttribute("shareMap", groupService.getMemberShares(groupName));


        return "grouppage"; // group.html
    }
}
