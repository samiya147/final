package org.example.demooooooo.Controller;

import org.example.demooooooo.Entity.users1;
import org.example.demooooooo.Service.SafeZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class trackercontroller {

    @Autowired
    private SafeZoneService safeZoneService;

    // ================= DASHBOARD =================
    @GetMapping("/trackerdash")
    public String trackerDashboard(HttpSession session, Model model) {

        users1 user = (users1) session.getAttribute("user");
        if (user == null) return "redirect:/";

        model.addAttribute("user", user);
        model.addAttribute("location", "Thane, India");
        model.addAttribute("gpsStatus", "Strong");

        int zoneCount = safeZoneService.getZonesByUser(user.getId()).size();
        model.addAttribute("zones", zoneCount);

        model.addAttribute("lat", 19.2183);
        model.addAttribute("lng", 72.9781);

        return "trackerdash";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();   // clears session
        return "redirect:/";    // goes to login page
    }

    // ================= LOCATION API =================
    @ResponseBody
    @GetMapping("/tracker/location")
    public Map<String, Double> getChildLocation(HttpSession session) {

        if (childLat == 0) {
            childLat = 19.2183;
            childLng = 72.9781;
        }

        childLat += (Math.random() - 0.5) * 0.0005;
        childLng += (Math.random() - 0.5) * 0.0005;

        users1 user = (users1) session.getAttribute("user");
        if (user != null) {
            safeZoneService.updateChildStatus(user.getId(), childLat, childLng);
        }

        Map<String, Double> map = new HashMap<>();
        map.put("lat", childLat);
        map.put("lng", childLng);
        return map;
    }
    // ✅ For browser testing
    @GetMapping("/api/gps")
    @ResponseBody
    public String testGPS(@RequestParam double lat,
                          @RequestParam double lng) {

        this.childLat = lat;
        this.childLng = lng;

        return "GET Working: " + lat + ", " + lng;
    }

    // ✅ For Postman / ESP32
    @PostMapping("/api/gps")
    @ResponseBody
    public String receiveGPS(@RequestParam double lat,
                             @RequestParam double lng) {

        this.childLat = lat;
        this.childLng = lng;

        System.out.println("POST Received: " + lat + ", " + lng);

        return "OK";
    }

    private double childLat = 0;
    private double childLng = 0;
}