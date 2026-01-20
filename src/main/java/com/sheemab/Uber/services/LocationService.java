package com.sheemab.Uber.services;

import com.sheemab.Uber.dto.DriverLocationDto;

import java.util.List;

public interface LocationService {

    Boolean saveDriverLocation(Integer driverId, Double latitude, Double longitude);

    List<DriverLocationDto> getNearbyDrivers(Double latitude, Double longitude, Double radius);

}
