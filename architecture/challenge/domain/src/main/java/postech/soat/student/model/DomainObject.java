package postech.soat.student.model;

public abstract class DomainObject {
    protected abstract void validateState() throws DomainValidationException;
}
