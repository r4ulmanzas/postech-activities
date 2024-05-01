package postech.soat.api.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public CreateStudentResponse createStudent(@RequestBody CreateStudentRequest payload) {
        var student = createStudentUseCase.create(payload.name(), payload.email(), payload.phone());
        return new CreateStudentResponse(student);
    }
}
