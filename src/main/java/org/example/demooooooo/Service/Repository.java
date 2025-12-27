package org.example.demooooooo.Service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Repository extends JpaRepository<Detail,String>
{
    boolean existsByEmail(String email);
    Optional<Detail> findByEmail(String email);
}
