package business.api.exceptions;

public class NotFoundLessonIdException extends ApiException {

	private static final long serialVersionUID = 2050228254550241644L;

	public static final String DESCRIPTION = "No se encuentra el identificador de clase utilizado";

    public static final int CODE = 333;

    public NotFoundLessonIdException() {
        this("");
    }

    public NotFoundLessonIdException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }
}
