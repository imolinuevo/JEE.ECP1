package business.api.exceptions;

public class JoinLessonException extends ApiException {

	private static final long serialVersionUID = 582303719170536626L;

	public static final String DESCRIPTION = "No se puede apuntar a esa clase de padel";

    public static final int CODE = 1;
	
	public JoinLessonException() {
        this("");
    }

	public JoinLessonException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }
}
