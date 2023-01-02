package ebolasafe.models;

import java.util.Date;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
public class Temperature {

	static final Logger LOG = Logger.getLogger(Temperature.class.getName());
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long Id;
	@Column(nullable = false)
	protected String username;
	@Column(nullable = false)
	float temp;
	@Column(name = "phone")
	protected String phone;
	@Temporal(TemporalType.DATE)
	Date date_taken;

	public enum time {
		morning, evening
	};

	@Enumerated(EnumType.ORDINAL)
	public time _time;

	public void setTemp(float temp) {
		int upper_celsius = 45, upper_farenheit = 113;
		int lower_celsius = 25, lower_farenheut = 77;

		if (temp >= lower_celsius && temp <= upper_celsius) {
			this.temp = temp;

		} else if (temp >= lower_farenheut && temp <= upper_farenheit) {

			this.temp = calculate_temp(temp);
			// LOG.info("check out..."+temp);

		}
	}

	public float calculate_temp(float _temp) {
		
		return   5*(_temp - 32)/9;
	}

}
