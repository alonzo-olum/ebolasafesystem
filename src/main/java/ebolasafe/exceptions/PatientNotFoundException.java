package ebolasafe.exceptions;

public class PatientNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PatientNotFoundException(String username) {
		super(String.format("Patient not avaiable! (Patient id: %s)", username));
	}

	public PatientNotFoundException() {
		// TODO Auto-generated constructor stub
	}
}
