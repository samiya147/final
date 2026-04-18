package org.example.demooooooo.Controller;

import org.example.demooooooo.Entity.LocationHistory;
import org.example.demooooooo.Entity.SafeZone;
import org.example.demooooooo.Entity.users1;
import org.example.demooooooo.Repository.LocationHistoryRepository;
import org.example.demooooooo.Service.SafeZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.OutputStream;
import jakarta.servlet.http.HttpSession;

import javax.xml.stream.Location;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class trackercontroller {

    @Autowired
    private SafeZoneService safeZoneService;
    @Autowired
    private LocationHistoryRepository locationRepository;

    private double childLat = 0;
    private double childLng = 0;

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

        model.addAttribute("lat", childLat);
        model.addAttribute("lng", childLng);

        return "trackerdash";
    }

    // ================= LOGOUT =================
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    // ================= LOCATION API =================
    @ResponseBody
    @GetMapping("/tracker/location")
    public Map<String, Double> getChildLocation(HttpSession session) {

        users1 user = (users1) session.getAttribute("user");

        if (user != null) {
            safeZoneService.updateChildStatus(user.getId(), childLat, childLng);
        }

        Map<String, Double> map = new HashMap<>();
        map.put("lat", childLat);
        map.put("lng", childLng);

        return map;
    }

    // ================= TEST API =================
    @GetMapping("/api/gps")
    @ResponseBody
    public String testGPS(@RequestParam double lat,
                          @RequestParam double lng) {

        this.childLat = lat;
        this.childLng = lng;

        return "GET Working: " + lat + ", " + lng;
    }

    // ================= MAIN GPS API (ESP32) =================
    @PostMapping("/api/gps")
    @ResponseBody
    public Map<String, Object> receiveGPS(@RequestBody Map<String, Object> data) {

        double lat = Double.parseDouble(data.get("lat").toString());
        double lng = Double.parseDouble(data.get("lng").toString());

        this.childLat = lat;
        this.childLng = lng;

        System.out.println("Received: " + lat + ", " + lng);

        // ✅ FIX: NO SESSION USED
        Long userId = 1L;

        // 🔥 SAVE LOCATION
        LocationHistory loc = new LocationHistory();
        loc.setLatitude(lat);
        loc.setLongitude(lng);
        loc.setTimestamp(java.time.LocalDateTime.now());
        loc.setUserId(userId);

        locationRepository.save(loc);

        // 🔥 CHECK GEOFENCE
        boolean alert = safeZoneService.updateChildStatus(userId, lat, lng);

        System.out.println("ALERT STATUS: " + alert);

        Map<String, Object> res = new HashMap<>();
        res.put("alert", alert);

        return res;
    }
    @GetMapping("/tracker/path")
    @ResponseBody
    public List<LocationHistory> getPath(HttpSession session) {

        users1 user = (users1) session.getAttribute("user");
        if (user == null) return List.of();

        return locationRepository.findByUserIdOrderByTimestampAsc(user.getId());
    }
    // ================= OPTIONAL SMS METHOD =================
    public class SIM800LSMS {
        private static long lastSmsSentTime = 0;
        private static final long SMS_COOLDOWN_MS = 120000;
        public static void sendSMS(String message) {
            // ✅ Debounce — don't send if SMS was sent recently
            long now = System.currentTimeMillis();
            if (now - lastSmsSentTime < SMS_COOLDOWN_MS) {
                System.out.println("⏳ SMS cooldown active, skipping...");
                return;
            }

            try {
                String phone = "+919819067752";

                Process process = Runtime.getRuntime().exec("cat > /dev/ttyUSB0");
                OutputStream os = process.getOutputStream();

                os.write("AT\r\n".getBytes());          Thread.sleep(1000);
                os.write("AT+CMGF=1\r\n".getBytes());   Thread.sleep(1000);
                os.write(("AT+CMGS=\"" + phone + "\"\r\n").getBytes());
                Thread.sleep(3000);

                os.write(message.getBytes());            Thread.sleep(1000);
                os.write(26);  // CTRL+Z
                Thread.sleep(5000);

                os.flush();
                os.close();

                lastSmsSentTime = now; // ✅ Update cooldown timer
                System.out.println("✅ SMS Sent!");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}