package com.sheemab.Uber.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDto {

    @NotNull(message = "Passenger ID is required")
    private Long passengerId;

    private Long driverId;

    @NotNull(message = "Pickup location lat is required")
    private Double pickupLocationLatitude;

    @NotNull(message = "Pickup location long is required")
    private Double pickupLocationLongitude;

    private String dropoffLocation;

    private BigDecimal fare;

    private LocalDateTime scheduledPickupTime;
}
