package org.example.demooooooo.Service;

import org.example.demooooooo.Entity.Detail;
import org.example.demooooooo.Entity.Expense;
import org.example.demooooooo.Entity.Expensesplit;
import org.example.demooooooo.Entity.groupdetail;
import org.example.demooooooo.Repository.addexpenserpository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class addexpenseservice
{

    private final addexpenserpository addexpenserep;


    public addexpenseservice(addexpenserpository addexpenserep) {

        this.addexpenserep = addexpenserep;

    }

    public  void addExpense(Expense expense, List<Detail> members)
    {


        double share=expense.getAmount()/members.size();
        for(Detail detail:members)
        {
            Expensesplit expensesplit=new Expensesplit();
            expensesplit.setExpense(expense);
            expensesplit.setMember(detail);
            expensesplit.setShareAmount(share);
             expense.getSplits().add(expensesplit);

        }


        addexpenserep.save(expense);

    }
}
