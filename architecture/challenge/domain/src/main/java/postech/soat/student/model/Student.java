package postech.soat.student.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Student {
    private String name;
    private String email;
    private String phone;

    @Override
    public String toString() {
        return "Student [name=" + name + ", email=" + email + ", phone=" + phone + "]";
    }
}
