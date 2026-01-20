package com.sheemab.Uber.repository;

import com.sheemab.Uber.entity.Booking;
import com.sheemab.Uber.entity.Driver;
import com.sheemab.Uber.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

        List<Booking> findByPassenger(Passenger passenger);
        List<Booking> findByDriver(Driver driver);
        Optional<Booking> findByIdAndPassenger(Long id, Passenger passenger);
        Optional<Booking> findByIdAndDriver(Long id, Driver driver);
}

