package postech.soat.student.model.valueobject;

import lombok.Getter;
import postech.soat.student.model.DomainObject;
import postech.soat.student.model.DomainValidationException;

@Getter
public class FullName extends DomainObject {
    private final String firstName;
    private final String lastName;

    public FullName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        validateState();
    }

    @Override
    protected void validateState() throws DomainValidationException {
        if (firstName == null || firstName.isBlank()) {
            throw new DomainValidationException("firstName", " is required");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new DomainValidationException("lastName", " is required");
        }
    }
}
