package postech.soat.student.model.entities;

import lombok.Getter;
import postech.soat.student.model.valueobject.FullName;

@Getter
public class Student {
    private Long id;
    private FullName name;
    private String email;
    private String phone;

    public Student(FullName name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", email=" + email + ", phone=" + phone + "]";
    }
}
