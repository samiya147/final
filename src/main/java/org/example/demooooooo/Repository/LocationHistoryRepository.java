package org.example.demooooooo.Repository;

import org.example.demooooooo.Entity.LocationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationHistoryRepository extends JpaRepository<LocationHistory, Long> {
    List<LocationHistory> findByUserIdOrderByTimestampAsc(Long userId);
}