package com.example.cam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CameraService {

    @Autowired
    private CameraRepository cameraRepository;

    private static final double THRESHOLD_DISTANCE = 100; // 100 meters

    public boolean isUserNearCamera(double userLat, double userLon) {
        List<Camera> cameras = cameraRepository.findAll();

        for (Camera camera : cameras) {
            double distance = calculateDistance(userLat, userLon, camera.getLatitude(), camera.getLongitude());
            if (distance <= THRESHOLD_DISTANCE) {
                return true;
            }
        }
        return false;
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371000; // Earth radius in meters
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}
