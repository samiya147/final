package org.example.demooooooo.Repository;

import org.example.demooooooo.Entity.Detail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<Detail,String>
{
    boolean existsByEmail(String email);
    Optional<Detail> findByEmail(String email);

    List<Detail> findAllByEmailIn(List<String> email1);
}
