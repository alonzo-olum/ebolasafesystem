package ebolasafe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ebolasafe.dto.resources.LocationResource;
import ebolasafe.exceptions.LocationNotFound;
import ebolasafe.models.Location;
import ebolasafe.repositories.LocationRepository;

@Service
public class LocationManager {

	@Autowired
	LocationRepository locationrepository;

	public Location createLocation(LocationResource _locRes) {
		Location location = new Location();
		location.setCountry(_locRes.getCountry());
		location.setTown(_locRes.getTown());
		location.setProvince(_locRes.getProvince());
		locationrepository.save(location);
		return location;

	}

	public List<Location> getAllLocation() {
		List<Location> location = this.locationrepository.findAll();
		return location;
	}

	public Location getLocationById(Long id) {
		Location location = this.locationrepository.findById(id);
		return location;
	}

	public List<Location> getLocation(String country) {

		List<Location> location = this.locationrepository
				.findByCountry(country);
		if (location == null) {
			throw new LocationNotFound("No location for this country!");
		}
		return location;
	}

	public List<Location> getLocationAstown(String town) {

		List<Location> location = this.locationrepository.findByTown(town);
		if (location == null) {
			throw new LocationNotFound("No location for this country!");
		}
		return location;
	}

	public Location updateLocation(Long id, LocationResource locRes) {
		Location location = this.locationrepository.findOne(id);
		location.setCountry(locRes.getCountry());
		location.setProvince(locRes.getProvince());
		location.setTown(locRes.getTown());
		locationrepository.save(location);
		return location;
	}

	public void deleteLocation(Long id) {
		locationrepository.delete(id);
	}
}
