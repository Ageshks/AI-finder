package com.example.cam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/cameras")
public class CameraController {

    private final CameraService cameraService;

    public CameraController(CameraService cameraService) {
        this.cameraService = cameraService;
    }

    // @GetMapping("/check")
    // public ResponseEntity<Boolean> checkProximity(@RequestParam double lat, @RequestParam double lon) {
    //     boolean isNear = cameraService.isNearCamera(lat, lon);
    //     return ResponseEntity.ok(isNear);
    // }

    @GetMapping("/nearby")
    public ResponseEntity<Map<String, Boolean>> checkNearbyCameras(
            @RequestParam double userLat, 
            @RequestParam double userLon) {

        boolean isNearby = cameraService.isUserNearCamera(userLat, userLon);
        Map<String, Boolean> response = new HashMap<>();
        response.put("nearby", isNearby);

        return ResponseEntity.ok(response);
    }

    // @PostMapping("/add")
    // public Camera addCamera(@RequestBody Camera camera) {
    //     return cameraService.addCamera(camera);
    // }
}
