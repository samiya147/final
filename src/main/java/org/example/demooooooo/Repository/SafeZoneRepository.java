package org.example.demooooooo.Repository;

import org.example.demooooooo.Entity.SafeZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SafeZoneRepository extends JpaRepository<SafeZone, Long> {

    List<SafeZone> findByUserId(Long userId);

    Optional<SafeZone> findByIdAndUserId(Long id, Long userId);

    long countByUserId(Long userId);
}