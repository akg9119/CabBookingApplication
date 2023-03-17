package com.cabBooking.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
@Entity
public class CabDriver {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String gender;
	private String vehicleDetails;
	@OneToOne
	private DriverLocation currentLocation;
	private boolean isAvailable;

	public CabDriver(String name, String gender, String vehicleDetails, ULocation currentLocation) {
		this.name = name;
		this.gender = gender;
		this.vehicleDetails = vehicleDetails;
		this.isAvailable = true;
	}

	public CabDriver() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getVehicleDetails() {
		return vehicleDetails;
	}

	public void setVehicleDetails(String vehicleDetails) {
		this.vehicleDetails = vehicleDetails;
	}

	public DriverLocation getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(DriverLocation currentLocation) {
		this.currentLocation = currentLocation;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
}
