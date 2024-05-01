package postech.soat.api.student;


import postech.soat.student.model.Student;

public record CreateStudentResponse(String name, String email, String phone) {

    public CreateStudentResponse(Student student) {
        this(student.getName(), student.getEmail(), student.getPhone());
    }
}
