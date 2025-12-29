package org.example.demooooooo.Entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Expense
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Double amount;
    @ManyToOne
    @JoinColumn(name = "paid_by")
     private Detail pay;
    @ManyToOne
    @JoinColumn(name ="group_id")
     private groupdetail group;
    @OneToMany(mappedBy = "expense", cascade = CascadeType.ALL)
    private List<Expensesplit> splits=new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Detail getPay() {
        return pay;
    }

    public void setPay(Detail pay) {
        this.pay = pay;
    }

    public groupdetail getGroup() {
        return group;
    }

    public void setGroup(groupdetail group) {
        this.group = group;
    }

    public List<Expensesplit> getSplits() {
        return splits;
    }

    public void setSplits(List<Expensesplit> splits) {
        this.splits = splits;
    }
}

