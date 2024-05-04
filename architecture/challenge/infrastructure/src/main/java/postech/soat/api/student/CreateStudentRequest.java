package postech.soat.api.student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateStudentRequest(
        @NotBlank(message = "Name is mandatory")
        String name,
        @NotBlank(message = "Email is mandatory")
        @Email(message = "Email should be valid")
        String email,
        @Pattern(regexp = "^[0-9]{11}$", message = "Phone should be 11 digits, the brazilian format")
        String phone) {
}
