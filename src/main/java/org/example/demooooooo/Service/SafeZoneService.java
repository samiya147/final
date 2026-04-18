package org.example.demooooooo.Service;

import org.example.demooooooo.Entity.SafeZone;
import org.example.demooooooo.Repository.SafeZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.time.LocalTime;

@Service
public class SafeZoneService {

    @Autowired
    private SafeZoneRepository safeZoneRepository;

    public List<SafeZone> getZonesByUser(Long userId) {
        return safeZoneRepository.findByUserId(userId);
    }

    public Optional<SafeZone> getZoneByIdAndUser(Long zoneId, Long userId) {
        return safeZoneRepository.findByIdAndUserId(zoneId, userId);
    }

    public SafeZone createZone(SafeZone zone) {
        return safeZoneRepository.save(zone);
    }

    public boolean deleteZone(Long zoneId, Long userId) {
        Optional<SafeZone> zone = safeZoneRepository.findByIdAndUserId(zoneId, userId);
        if (zone.isPresent()) {
            safeZoneRepository.delete(zone.get());
            return true;
        }
        return false;
    }

    /**
     * 🔥 MAIN METHOD: Update child status + detect alert
     */
    public boolean updateChildStatus(Long userId, double childLat, double childLng) {

        List<SafeZone> zones = safeZoneRepository.findByUserId(userId);

        // 🔥 STEP 1: Get active zone
        SafeZone activeZone = getActiveZone(zones);

        if (activeZone == null) {
            System.out.println("No active zone right now");
            return false; // no alert
        }

        boolean inside = false;

        // 🔥 STEP 2: Check only ACTIVE zone
        if ("CIRCLE".equalsIgnoreCase(activeZone.getZoneType())) {

            double dist = haversineDistance(
                    activeZone.getLatitude(),
                    activeZone.getLongitude(),
                    childLat,
                    childLng
            );

            inside = dist <= activeZone.getRadius();

            // DEBUG
            System.out.println("------ ACTIVE ZONE CHECK ------");
            System.out.println("Zone Name: " + activeZone.getName());
            System.out.println("Current Time: " + java.time.LocalTime.now());
            System.out.println("Distance: " + dist);
            System.out.println("Inside: " + inside);
        }

        // 🔥 STEP 3: Update ONLY active zone
        activeZone.setChildInside(inside);
        safeZoneRepository.save(activeZone);

        // 🔥 STEP 4: Alert if outside
        return !inside;
    }

    private double haversineDistance(double lat1, double lon1, double lat2, double lon2) {

        final int R = 6371000;

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        return R * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    }
    public SafeZone getActiveZone(List<SafeZone> zones) {

        LocalTime currentTime = LocalTime.now();

        for (SafeZone zone : zones) {
            if (!currentTime.isBefore(zone.getStartTime()) &&
                    !currentTime.isAfter(zone.getEndTime())) {

                return zone; // only one active
            }
        }

        return null; // no active zone
    }
}