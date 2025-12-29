package org.example.demooooooo.Entity;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;

@Entity
public class Expensesplit
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "expense_id")
    private Expense expense;
    @ManyToOne
    @JoinColumn(name = "detail_id")
    private Detail member;

    private Double shareAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public Detail getMember() {
        return member;
    }

    public void setMember(Detail member) {
        this.member = member;
    }

    public Double getShareAmount() {
        return shareAmount;
    }

    public void setShareAmount(Double shareAmount) {
        this.shareAmount = shareAmount;
    }
}
