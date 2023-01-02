package ebolasafe.models;

import java.util.logging.Logger;

import org.joda.time.Days;
import org.joda.time.LocalDate;

public class Content {
	static final Logger LOG = Logger.getLogger(Content.class.getName());
	int days;
	int isolation_days = 21;
		
	public String getContent(int status, String username, LocalDate _date) {

		LocalDate daytoday = LocalDate.now();

		days = Days.daysBetween(_date, daytoday).getDays();
		int days_remaining = isolation_days - days;
		LOG.info("the no of days in isolation..." + days);
		String themessage = null; 

		String NOTIFY = "Hello,"
				+ username
				+ " x x x  has included you as her next of kin on EbolaApp."
				+ " She is going into self isolation for 21 days as she feels she has been in contact with the Ebola virus. We shall update you on her condition over the next 21 days."
				+ "If"
				+ username
				+ " begins to show signs of infection during this 21 day period, we shall immediately pass this information including your details to the nearest medical respondents in your area and they will get her the medical attention requires as soon as possible";

		String WELCOME = "Hi"
				+ " "
				+ username
				+ ",thank you for using EbolApp. We hope to keep you and your loved ones  safe and informed during your isolation period and will remind you to update your temperature twice daily and to report on any symptoms daily.";
		String RAINY_SCENARIO = "Dear "
				+ username
				+ " ,your symptoms suggest you are infected, your next of kin and the medics in your area have been alerted and will get you medical attention. Don't worry, your symptoms have been discovered early and you stand a chance of fighting the virus.";
		String SUNNY_SCENARIO = "Congratulations"
				+ username
				+ " ,it appears you are fine as you have not shown any symptoms during this period. Super! Please speread the word of EbolApp to your community so that they may also keep each other safe.";

		String PERIOD_NOTE = "Hi,"
				+ " "
				+ username
				+ ",you are now "
				+ days
				+ " days from your date of suspected infection, It is only "
				+ days_remaining
				+ " days to go before you should come out of isolation. Stay strong and keep up the good job of sending your daily temperatures and symptoms.";
		String CONCLUSION = "Hi," +" "+username+"kindly refer to your last message to check your status, as your time in Isolation is now over.";
		
				
		//Return message if its the isolation last day
		if (days_remaining == 0) {

			if (status == 3) {
				themessage = SUNNY_SCENARIO;
				

			} else if (status == 4) {
				themessage = RAINY_SCENARIO;
				
			}
		}
		//Return message if its past the isolation last day
		
		if(days_remaining < 0 && status != 0){
			
			if(status == 3 ){
				themessage = SUNNY_SCENARIO;
				
			}else if(status == 4){
				themessage = RAINY_SCENARIO;
				
			}
			else if(status == 5){
				themessage = PERIOD_NOTE;
			}
				
			
		}
		//Returns message if its days before isolation last date
		if(days_remaining > 0){ 
			
			if (status == 1) {
				themessage = WELCOME;
							
			} else if (status == 2) {
				themessage = NOTIFY;
				
			} else if (status == 5) {
				themessage = PERIOD_NOTE;
				
			}
		}
		
		return themessage;
	}

}

