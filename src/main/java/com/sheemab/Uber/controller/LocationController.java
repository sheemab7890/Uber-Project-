package com.sheemab.Uber.controller;



import java.util.List;

import com.sheemab.Uber.dto.DriverLocationDto;
import com.sheemab.Uber.dto.NearbyDriverRequestDto;
import com.sheemab.Uber.services.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/location")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @PostMapping("/driverLocation")
    public ResponseEntity<Boolean> saveDriverLocation(@RequestBody DriverLocationDto driverLocation) {
        Boolean saved = locationService.saveDriverLocation(driverLocation.getDriverId(), driverLocation.getLatitude(), driverLocation.getLongitude());
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/nearbyDrivers")
    public ResponseEntity<List<DriverLocationDto>> getNearbyDrivers(@RequestBody NearbyDriverRequestDto request) {
        List<DriverLocationDto> nearbyDrivers = locationService.getNearbyDrivers(request.getLatitude(), request.getLongitude(), request.getRadius());
        return ResponseEntity.ok(nearbyDrivers);
    }


}
