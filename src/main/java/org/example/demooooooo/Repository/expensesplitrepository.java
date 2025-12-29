package org.example.demooooooo.Repository;

import org.example.demooooooo.Entity.Expensesplit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface expensesplitrepository extends JpaRepository<Expensesplit,Long>
{

}
