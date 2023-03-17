package com.cabBooking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cabBooking.Entity.DriverLocation;

public interface DriverLocationRepository extends JpaRepository<DriverLocation, Integer> {

}
