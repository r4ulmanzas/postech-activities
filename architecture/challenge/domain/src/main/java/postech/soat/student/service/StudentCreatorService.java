package postech.soat.student.service;

import postech.soat.student.model.entities.Student;
import postech.soat.student.model.valueobject.FullName;

public class StudentCreatorService implements CreateStudentUseCase {

    @Override
    public Student create(String firstName, String lastName, String email, String phone) {
        var name = new FullName(firstName, lastName);
        var newStudent = new Student(name, email, phone);
        System.out.println(newStudent);
        return newStudent;
    }
}
