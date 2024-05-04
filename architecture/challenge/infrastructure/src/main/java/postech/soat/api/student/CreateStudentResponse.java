package postech.soat.api.student;


import postech.soat.student.model.entities.Student;

public record CreateStudentResponse(String firstName, String lastName, String email, String phone) {

    public CreateStudentResponse(Student student) {
        this(
                student.getName().getFirstName(),
                student.getName().getLastName(),
                student.getEmail(),
                student.getPhone()
        );
    }
}
