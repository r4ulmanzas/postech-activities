package postech.soat.api.student;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import postech.soat.api.ApiResponse;
import postech.soat.student.service.CreateStudentUseCase;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final CreateStudentUseCase createStudentUseCase;

    @Autowired
    public StudentController(CreateStudentUseCase createStudentUseCase) {
        this.createStudentUseCase = createStudentUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CreateStudentResponse> createStudent(
            @Valid @RequestBody CreateStudentRequest payload
    ) {
        var student = createStudentUseCase.create(
                payload.firstName(),
                payload.lastName(),
                payload.email(),
                payload.phone()
        );
        return new ApiResponse<>(new CreateStudentResponse(student));
    }
}
