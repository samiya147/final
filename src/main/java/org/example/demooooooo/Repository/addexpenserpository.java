package org.example.demooooooo.Repository;

import org.example.demooooooo.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface addexpenserpository extends JpaRepository<Expense,Long>
{

}
