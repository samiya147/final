package org.example.demooooooo.Repository;

import org.example.demooooooo.Entity.Expense;
import org.example.demooooooo.Entity.Expensesplit;
import org.example.demooooooo.Entity.groupdetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface expensesplitrepository extends JpaRepository<Expensesplit,Long>
{


}
