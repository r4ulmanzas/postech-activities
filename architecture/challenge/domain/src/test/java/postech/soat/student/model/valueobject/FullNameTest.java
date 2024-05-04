package postech.soat.student.model.valueobject;

import net.datafaker.Faker;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import postech.soat.student.model.DomainValidationException;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FullNameTest {

    private final Faker faker = new Faker();

    private static Stream<String> testCases() {
        return Stream.of(
                "", " ",  null
        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    public void whenFirstNameIsEmpty_ThenADomainValidationExceptionMustBeThrown(String firstName) {
        var lastName = faker.bigBangTheory().character();

        var exception = assertThrows(DomainValidationException.class, () -> new FullName(firstName, lastName));

        assertEquals("Error validating field firstName:  is required", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("testCases")
    public void whenLastNameIsEmpty_ThenADomainValidationExceptionMustBeThrown(String lastName) {
        var firstName = faker.bigBangTheory().character();

        var exception = assertThrows(DomainValidationException.class, () -> new FullName(firstName, lastName));

        assertEquals("Error validating field lastName:  is required", exception.getMessage());
    }
}
