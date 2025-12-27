package org.example.demooooooo.Service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface grouprepository extends JpaRepository<groupdetail,String>
{

}
