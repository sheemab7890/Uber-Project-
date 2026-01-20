package com.sheemab.Uber.mapper;


import com.sheemab.Uber.dto.BookingRequestDto;
import com.sheemab.Uber.dto.BookingResponseDto;
import com.sheemab.Uber.entity.Booking;
import com.sheemab.Uber.entity.Driver;
import com.sheemab.Uber.entity.Passenger;
import com.sheemab.Uber.enums.BookingStatus;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public Booking toEntity(BookingRequestDto request, Passenger passenger, Driver driver) {
        BookingStatus status = driver != null ? BookingStatus.CONFIRMED : BookingStatus.PENDING;

        return Booking.builder()
                .passenger(passenger)
                .driver(driver)
                .pickupLocationLatitude(request.getPickupLocationLatitude().toString())
                .pickupLocationLongitude(request.getPickupLocationLongitude().toString())
                .dropoffLocation(request.getDropoffLocation())
                .fare(request.getFare())
                .status(status)
                .scheduledPickupTime(request.getScheduledPickupTime())
                .build();
    }

    public BookingResponseDto toResponseDto(Booking booking) {
        return BookingResponseDto.builder()
                .id(booking.getId())
                .passengerId(booking.getPassenger() != null ? booking.getPassenger().getId() : null)
                .passengerName(booking.getPassenger() != null ? booking.getPassenger().getName() : null)
                .driverId(booking.getDriver() != null ? booking.getDriver().getId() : null)
                .driverName(booking.getDriver() != null ? booking.getDriver().getName() : null)
                .pickupLocationLatitude(Double.parseDouble(booking.getPickupLocationLatitude()))
                .pickupLocationLongitude(Double.parseDouble(booking.getPickupLocationLongitude()))
                .dropoffLocation(booking.getDropoffLocation())
                .status(booking.getStatus())
                .fare(booking.getFare())
                .createdAt(booking.getCreatedAt())
                .updatedAt(booking.getUpdatedAt())
                .scheduledPickupTime(booking.getScheduledPickupTime())
                .actualPickupTime(booking.getActualPickupTime())
                .completedAt(booking.getCompletedAt())
                .build();
    }

    public void updateEntity(Booking booking, BookingRequestDto request, Passenger passenger, Driver driver) {
        booking.setPassenger(passenger);
        booking.setDriver(driver);
        booking.setPickupLocationLatitude(request.getPickupLocationLatitude().toString());
        booking.setPickupLocationLongitude(request.getPickupLocationLongitude().toString());
        booking.setDropoffLocation(request.getDropoffLocation());
        booking.setFare(request.getFare());
        booking.setScheduledPickupTime(request.getScheduledPickupTime());

        // Update status based on driver assignment
        if (driver != null && booking.getStatus() == BookingStatus.PENDING) {
            booking.setStatus(BookingStatus.CONFIRMED);
        }
    }
}

