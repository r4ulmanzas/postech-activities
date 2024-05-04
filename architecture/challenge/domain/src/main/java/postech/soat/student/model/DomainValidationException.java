package postech.soat.student.model;

public class DomainValidationException extends RuntimeException {
    public DomainValidationException(String field, String message) {
        super(String.format("Error validating field %s: %s", field, message));
    }
}
