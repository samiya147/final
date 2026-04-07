package org.example.demooooooo.Controller;

import org.example.demooooooo.Entity.SafeZone;
import org.example.demooooooo.Entity.users1;
import org.example.demooooooo.Service.SafeZoneService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SetZoneController {

    @Autowired
    private SafeZoneService safeZoneService;

    private users1 getLoggedInUser(HttpSession session) {
        return (users1) session.getAttribute("user");
    }

    // ================= ZONES PAGE =================
    @GetMapping("/zones")
    public String zonesPage(Model model, HttpSession session) {
        users1 user = getLoggedInUser(session);
        if (user == null) return "redirect:/";

        List<SafeZone> zones = safeZoneService.getZonesByUser(user.getId());

        model.addAttribute("zones", zones);
        model.addAttribute("zoneCount", zones.size());
        model.addAttribute("user", user);

        return "zones";
    }

    // ================= ADD ZONE PAGE =================
    @GetMapping("/zones/add")
    public String addZonePage(Model model, HttpSession session) {
        users1 user = getLoggedInUser(session);
        if (user == null) return "redirect:/";

        model.addAttribute("user", user);
        return "addzone";
    }

    // ================= SAVE ZONE =================
    @PostMapping("/zones/save")
    public String saveZone(
            @RequestParam String name,
            @RequestParam String zoneType,
            @RequestParam Double latitude,
            @RequestParam Double longitude,
            @RequestParam(defaultValue = "500") Integer radius,
            @RequestParam(required = false) String sourceName,
            @RequestParam(required = false) String destinationName,
            @RequestParam(required = false) Double sourceLat,
            @RequestParam(required = false) Double sourceLng,
            @RequestParam(required = false) Double destLat,
            @RequestParam(required = false) Double destLng,
            HttpSession session
    ) {
        users1 user = getLoggedInUser(session);
        if (user == null) return "redirect:/";

        SafeZone zone = new SafeZone();
        zone.setName(name);
        zone.setZoneType(zoneType.toUpperCase());
        zone.setLatitude(latitude);
        zone.setLongitude(longitude);
        zone.setRadius(radius);
        zone.setUserId(user.getId());
        zone.setChildInside(false);

        if ("ROUTE".equalsIgnoreCase(zoneType)) {
            zone.setSourceName(sourceName);
            zone.setDestinationName(destinationName);
            zone.setSourceLat(sourceLat);
            zone.setSourceLng(sourceLng);
            zone.setDestLat(destLat);
            zone.setDestLng(destLng);
        }

        safeZoneService.createZone(zone);

        return "redirect:/zones?success=true";
    }

    // ================= DELETE =================
    @PostMapping("/zones/delete/{id}")
    public String deleteZone(@PathVariable Long id, HttpSession session) {
        users1 user = getLoggedInUser(session);
        if (user == null) return "redirect:/";

        safeZoneService.deleteZone(id, user.getId());
        return "redirect:/zones";
    }
}