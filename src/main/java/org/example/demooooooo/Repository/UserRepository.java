package org.example.demooooooo.Repository;

import org.example.demooooooo.Entity.users1;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<users1, Long> {

    users1 findByUsername(String username);
}