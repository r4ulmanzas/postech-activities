package postech.soat.student.service;

import postech.soat.student.model.Student;

public class StudentCreatorService implements CreateStudentUseCase {

    @Override
    public Student create(String name, String email, String phone) {
        var newStudent = new Student(name, email, phone);
        System.out.println(newStudent);
        return newStudent;
    }
}
