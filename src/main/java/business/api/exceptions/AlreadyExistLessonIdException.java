package business.api.exceptions;

public class AlreadyExistLessonIdException extends ApiException {

	private static final long serialVersionUID = -798236239064168845L;

	public static final String DESCRIPTION = "Ya existe la clase de padel";

    public static final int CODE = 1;
	
	public AlreadyExistLessonIdException() {
        this("");
    }

	public AlreadyExistLessonIdException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
