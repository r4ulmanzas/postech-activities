package postech.soat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import postech.soat.student.service.StudentCreatorService;

@Configuration
public class BeanConfiguration {

    @Bean
    StudentCreatorService studentCreatorService() {
        return new StudentCreatorService();
    }
}
