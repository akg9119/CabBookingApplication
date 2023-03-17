package com.cabBooking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cabBooking.Entity.ULocation;

public interface LocationRepository extends JpaRepository<ULocation, Integer> {

}
