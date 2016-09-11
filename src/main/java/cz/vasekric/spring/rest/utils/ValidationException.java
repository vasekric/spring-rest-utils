package cz.vasekric.spring.rest.utils;

/**
 * @author Richard Vašek.
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super( message );
    }

    public ValidationException() {
        super();
    }

    public ValidationException(String message, Throwable cause) {
        super( message, cause );
    }

    public ValidationException(Throwable cause) {
        super( cause );
    }
}