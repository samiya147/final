package org.example.demooooooo.Service;

import org.example.demooooooo.Entity.DashboardSummaryDTO;
import org.example.demooooooo.Entity.Detail;
import org.example.demooooooo.Entity.Expense;
import org.example.demooooooo.Entity.Expensesplit;
import org.example.demooooooo.Repository.addexpenserpository;
import org.example.demooooooo.Repository.expensesplitrepository;
import org.example.demooooooo.Repository.grouprepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class dasboardservice
{
    private final addexpenserpository expenseRepository;
    private final grouprepository groupRepository;
    private final expensesplitrepository expensesplitRepository;
    private final Repository repository;

    public dasboardservice(addexpenserpository expenseRepository, grouprepository groupRepository, expensesplitrepository expensesplitRepository, Repository repository) {
        this.expenseRepository = expenseRepository;
        this.groupRepository = groupRepository;
        this.expensesplitRepository = expensesplitRepository;
        this.repository = repository;
    }
   public dashbaord(Detail detail)
   {
       double youOwe = 0;
       double youAreOwed = 0;
       List<Expense> allExpenses=addexpenserpository.findAll();
       for(Expense expense:allExpenses)
       {
           for(Expensesplit split:expense.getSplits())
           {
               if(split.getMember().equals(detail))
               {
                   youOwe+=split.getShareAmount();
               }
               if(expense.getPay().equals(detail))
               {
                   youAreOwed+=split.getShareAmount();
               }
           }
       }
       DashboardSummaryDTO dto = new DashboardSummaryDTO();
       dto.setYouOwe(youOwe);
       dto.setYouAreOwed(youAreOwed);
       return dto;
   }


}
