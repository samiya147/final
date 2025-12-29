package org.example.demooooooo.Repository;


import org.example.demooooooo.Entity.groupdetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface grouprepository extends JpaRepository<groupdetail,String>
{



}
