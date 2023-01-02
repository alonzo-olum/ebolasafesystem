package ebolasafe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ebolasafe.dto.resources.TemperatureResource;
import ebolasafe.models.Temperature;
import ebolasafe.repositories.TemperatureRepository;
import ebolasafe.util.DataTypeConverter;

@Service
public class TemperatureManager {
	
	@Autowired
	TemperatureRepository tempRepo;

	public List<Temperature> getAllTemperatureReadings() {
		// TODO Auto-generated method stub
		return this.tempRepo.findAll();
	}

	public Temperature createTemperatureReading(
			TemperatureResource temperatuRes) {
		// TODO Auto-generated method stub
		Temperature temp = new Temperature();
		temp.setUsername(temperatuRes.getUsername());
		temp.setPhone(temperatuRes.getPhone());
		temp.setTemp(temperatuRes.getTemp());
		temp.setDate_taken(temperatuRes.getDate_taken());
		temp.set_time(temperatuRes.get_timeRes());
		return this.tempRepo.save(temp);
	}

	public List<Temperature> getTemperatureReading(String phone) {
		// TODO Auto-generated method stub
		return this.tempRepo.findByPhone(phone);
	}

	public Temperature updateTemperatureReading(
			Long id, TemperatureResource _TemperatureReading) {
		// TODO Auto-generated method stub
		Temperature temperature = tempRepo.findOne(id);
		temperature.setDate_taken(_TemperatureReading.getDate_taken());
		temperature.setTemp(_TemperatureReading.getTemp());
		temperature.setUsername(_TemperatureReading.getUsername());
		temperature.setPhone(_TemperatureReading.getPhone());
			return this.tempRepo.save(temperature);
	}

	public void deleteTemperatureReading(Long id) {
		// TODO Auto-generated method stub
		this.tempRepo.delete(id);
	}
	public List<Temperature> getDatereading(String phone, String date){
		
		java.sql.Date dater = DataTypeConverter.getDate(date);
		List<Temperature> Readings = this.tempRepo.datefinder(phone, dater);
		return Readings;
	}
}
