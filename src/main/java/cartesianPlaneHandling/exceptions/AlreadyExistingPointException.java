package cartesianPlaneHandling.exceptions;

public class AlreadyExistingPointException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public AlreadyExistingPointException() {}
	
	public AlreadyExistingPointException(String errorMessage) {
		super(errorMessage);
	}

}
