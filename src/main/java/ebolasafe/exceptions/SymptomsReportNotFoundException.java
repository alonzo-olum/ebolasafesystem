package ebolasafe.exceptions;

public class SymptomsReportNotFoundException extends RuntimeException{

	
	public SymptomsReportNotFoundException() {
		super("Message not avaiable!");
		// TODO Auto-generated constructor stub
	}
	public SymptomsReportNotFoundException(String username) {
		super(username);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2588400369829041321L;

}
