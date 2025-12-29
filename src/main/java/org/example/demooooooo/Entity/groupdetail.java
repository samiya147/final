package org.example.demooooooo.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class groupdetail
{
   @Id
   private  String group_name;
   @ManyToMany
    @JoinTable
            (
            name="group_members",
            joinColumns = @JoinColumn(name="group_name"),
            inverseJoinColumns = @JoinColumn(name="email")
    )
    private List<Detail> members;

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public List<Detail> getMembers() {
        return members;
    }

    public void setMembers(List<Detail> members) {
        this.members = members;
    }
}
