package org.example.demooooooo.Service;

import org.example.demooooooo.Entity.SafeZone;
import org.example.demooooooo.Repository.SafeZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
     * Called with the child's latest GPS coordinates.
     * Updates childInside flag on every zone for this user.
     * You can wire this into your existing /tracker/location polling.
     */
    public void updateChildStatus(Long userId, double childLat, double childLng) {
        List<SafeZone> zones = safeZoneRepository.findByUserId(userId);
        for (SafeZone zone : zones) {
            if ("CIRCLE".equals(zone.getZoneType())) {
                double dist = haversineDistance(zone.getLatitude(), zone.getLongitude(), childLat, childLng);
                zone.setChildInside(dist <= zone.getRadius());

            } else if ("ROUTE".equals(zone.getZoneType())) {
                double distSrc = haversineDistance(zone.getSourceLat(), zone.getSourceLng(), childLat, childLng);
                double distDst = haversineDistance(zone.getDestLat(), zone.getDestLng(), childLat, childLng);
                zone.setChildInside(distSrc <= zone.getRadius() || distDst <= zone.getRadius());
            }
            safeZoneRepository.save(zone);
        }
    }

    // Haversine formula — returns distance in metres between two lat/lng points
    private double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371000;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        return R * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    }
}