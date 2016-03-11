package business.api.exceptions;

public class DeleteStudentException extends ApiException {

	private static final long serialVersionUID = -6192980861151389587L;

	public static final String DESCRIPTION = "No se puede apuntar a esa clase de padel";

    public static final int CODE = 1;
	
	public DeleteStudentException() {
        this("");
    }

	public DeleteStudentException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }
}
