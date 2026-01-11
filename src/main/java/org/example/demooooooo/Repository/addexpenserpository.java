package org.example.demooooooo.Repository;

import org.example.demooooooo.Entity.Detail;
import org.example.demooooooo.Entity.Expense;
import org.example.demooooooo.Entity.groupdetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface addexpenserpository extends JpaRepository<Expense,Long>
{


    List<Expense> findByGroup(groupdetail group);
}
