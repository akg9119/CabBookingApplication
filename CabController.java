package com.cabBooking.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cabBooking.Entity.CabDriver;
import com.cabBooking.Entity.DriverLocation;
import com.cabBooking.Entity.ULocation;
import com.cabBooking.Entity.User;
import com.cabBooking.Repository.CabDriverRepository;
import com.cabBooking.Repository.DriverLocationRepository;
import com.cabBooking.Repository.LocationRepository;
import com.cabBooking.Repository.UserRepository;

@RestController
public class CabController {

	private Map<String, User> users = new HashMap<>();
	private List<CabDriver> drivers = new ArrayList<>();

	@Autowired
	CabDriverRepository dr;
	@Autowired
	LocationRepository lr;
	@Autowired
	UserRepository ur;
	@Autowired
	DriverLocationRepository drloc;

	@PostMapping("/users")
	public User addUser(@RequestBody User user) {
		users.put(user.getName(), user);
		return  ur.save(user);
		
	}
	public void saveLocation(ULocation loc) {
		lr.save(loc);
	}

	@PostMapping("/drivers")
	public CabDriver addDriver(@RequestBody CabDriver driver) {
		drivers.add(driver);
		return dr.save(driver);
	}
	List<CabDriver> availableDrivers=null;
	@GetMapping("/rides")
	public List<CabDriver> findRide(@RequestParam String username, @RequestParam double sourceLatitude,
			@RequestParam double sourceLongitude, @RequestParam double destLatitude,
			@RequestParam double destLongitude) {
			ULocation loc=new ULocation(destLatitude, destLongitude);
			saveLocation(loc);
			List<CabDriver>cd=dr.findAll();
			availableDrivers = new ArrayList<>();

		for (CabDriver driver : cd) {
			if (driver.isAvailable() && isNear(driver.getCurrentLocation(), sourceLatitude, sourceLongitude)) {
				availableDrivers.add(driver);
			}
		} 
	return availableDrivers;
	}
	@GetMapping("/bookRide")
	public String BookRide(@RequestParam String username,@RequestParam String driverName) {
		User u=ur.findUserByNameIgnoreCase(username);
		if(u!=null) {
		for(CabDriver cd:availableDrivers) {
			if(cd.getName().equalsIgnoreCase(driverName) && u.getName().equalsIgnoreCase(driverName)) {
				return "Ride is Booked";
			}
		}
		return "Ride is not Availble";
		}
		else 
			return "user is invalid";
	}
	private boolean isNear(DriverLocation location1, double latitude, double longitude) {
		ULocation loc=lr.findById(5).get();
		
		double distance = calculateDistance(loc.getLatitude(),loc.getLongitude() , latitude, longitude);
		return distance < 5.0;
		
	}
	private double calculateDistance(double latitude, double longitude, double latitude2, double longitude2) {
		 
		double dLat = Math.toRadians(latitude2 - latitude);
	    double dLon = Math.toRadians(longitude2 - longitude);

	    latitude = Math.toRadians(latitude);
	    latitude2 = Math.toRadians(latitude2);

	    double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(latitude)
	            * Math.cos(latitude2);
	    double c = 2 * Math.asin(Math.sqrt(a));
	    double distance =  c;
	    
	    return distance;
	}


}
