package postech.soat.student.service;

import postech.soat.student.model.Student;

public interface CreateStudentUseCase {
    public Student create(String name, String email, String phone);
}
