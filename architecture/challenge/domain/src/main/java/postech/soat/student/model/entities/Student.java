package postech.soat.student.model.entities;

import lombok.Getter;

@Getter
public class Student {
    private Long id;
    private String name;
    private String email;
    private String phone;

    public Student(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", email=" + email + ", phone=" + phone + "]";
    }
}
