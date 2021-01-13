package danyliuk.mykola.exceptions;

/**
 * Ресурс не знайдено
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
