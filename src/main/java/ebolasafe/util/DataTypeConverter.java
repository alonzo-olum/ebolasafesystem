package ebolasafe.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataTypeConverter {

	static public java.sql.Date getDate(String date){
		SimpleDateFormat smd = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		try {
			d = smd.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date _d = new java.sql.Date(d.getTime());
		_d = new java.sql.Date(d.getTime());
	
		System.out.println("#########"+_d);
		return _d;
		
	}
}
