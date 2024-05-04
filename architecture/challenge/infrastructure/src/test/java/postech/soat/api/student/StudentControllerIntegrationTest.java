package postech.soat.api.student;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import postech.soat.api.ApiError;
import postech.soat.api.ApiResponse;
import postech.soat.api.Status;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private final Faker faker = new Faker();

    @Test
    public void whenTryingToCreateStudentWithValidInputThenReturnCreated() throws Exception {
        var payload = new CreateStudentRequest(
                faker.gameOfThrones().character(),
                faker.hitchhikersGuideToTheGalaxy().character(),
                faker.internet().emailAddress(),
                "11999999999"
        );

        ObjectMapper objectMapper = new ObjectMapper();
        String payloadJson = objectMapper.writeValueAsString(payload);

        mockMvc.perform(MockMvcRequestBuilders.post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andExpect(status().isCreated())
                .andExpect(result -> {
                    var response = result.getResponse().getContentAsString();
                    var apiResponse = objectMapper.readValue(response,
                            new TypeReference<ApiResponse<CreateStudentResponse>>() {});

                    assertEquals(Status.SUCCESS, apiResponse.status);
                    assertNull(apiResponse.errors);

                    var student = (CreateStudentResponse) apiResponse.data;
                    assertEquals(student.email(), payload.email());
                    assertEquals(student.phone(), payload.phone());
                    assertEquals(student.firstName(), payload.firstName());
                    assertEquals(student.lastName(), payload.lastName());
                });
    }

    @ParameterizedTest
    @ValueSource(strings = { "",  " ", "                "})
    public void whenTryingToCreateAStudentWithoutAFirstNameThenReturnBadRequest(String firstName) throws Exception {
        var payload = new CreateStudentRequest(
                firstName,
                faker.hitchhikersGuideToTheGalaxy().character(),
                faker.internet().emailAddress(),
                "11999999999"
        );

        ObjectMapper objectMapper = new ObjectMapper();
        String payloadJson = objectMapper.writeValueAsString(payload);

        mockMvc.perform(MockMvcRequestBuilders.post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    var response = result.getResponse().getContentAsString();
                    var apiResponse = objectMapper.readValue(response,  new TypeReference<ApiResponse<ApiError>>() {});

                    assertEquals(Status.ERROR, apiResponse.status);
                    assertNull(apiResponse.data);
                    assertEquals(1, apiResponse.errors.size());
                    assertEquals("First name is mandatory", apiResponse.errors.getFirst().message);
                });
    }

    @ParameterizedTest
    @ValueSource(strings = { "",  " ", "                "})
    public void whenTryingToCreateAStudentWithoutALastNameThenReturnBadRequest(String lastName) throws Exception {
        var payload = new CreateStudentRequest(
                faker.hitchhikersGuideToTheGalaxy().character(),
                lastName,
                faker.internet().emailAddress(),
                "11999999999"
        );

        ObjectMapper objectMapper = new ObjectMapper();
        String payloadJson = objectMapper.writeValueAsString(payload);

        mockMvc.perform(MockMvcRequestBuilders.post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    var response = result.getResponse().getContentAsString();
                    var apiResponse = objectMapper.readValue(response,  new TypeReference<ApiResponse<ApiError>>() {});

                    assertEquals(Status.ERROR, apiResponse.status);
                    assertNull(apiResponse.data);
                    assertEquals(1, apiResponse.errors.size());
                    assertEquals("Last name is mandatory", apiResponse.errors.getFirst().message);
                });
    }

    @ParameterizedTest
    @ValueSource(strings = { "asdf@",  "asdfasdf ", "asdf.com", "asdf@.com"})
    public void whenTryingToCreateAStudentWithInvalidEmailThenReturnBadRequest(String email) throws Exception {
        var payload = new CreateStudentRequest(
                faker.hitchhikersGuideToTheGalaxy().character(),
                faker.howIMetYourMother().character(),
                email,
                "11999999999"
        );

        ObjectMapper objectMapper = new ObjectMapper();
        String payloadJson = objectMapper.writeValueAsString(payload);

        mockMvc.perform(MockMvcRequestBuilders.post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    var response = result.getResponse().getContentAsString();
                    var apiResponse = objectMapper.readValue(response,  new TypeReference<ApiResponse<ApiError>>() {});

                    assertEquals(Status.ERROR, apiResponse.status);
                    assertNull(apiResponse.data);
                    assertEquals(1, apiResponse.errors.size());
                    assertEquals("Email should be valid", apiResponse.errors.getFirst().message);
                });
    }

    @ParameterizedTest
    @ValueSource(strings = { " ", "                "} )
    public void whenTryingToCreateAStudentWithoutAnEmailThenReturnBadRequest(String email) throws Exception {
        var payload = new CreateStudentRequest(
                faker.hitchhikersGuideToTheGalaxy().character(),
                faker.howIMetYourMother().character(),
                email,
                "11999999999"
        );

        ObjectMapper objectMapper = new ObjectMapper();
        String payloadJson = objectMapper.writeValueAsString(payload);

        mockMvc.perform(MockMvcRequestBuilders.post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    var response = result.getResponse().getContentAsString();
                    var apiResponse = objectMapper.readValue(response,  new TypeReference<ApiResponse<ApiError>>() {});

                    assertEquals(Status.ERROR, apiResponse.status);
                    assertNull(apiResponse.data);
                    assertEquals(2, apiResponse.errors.size());
                    assertEquals("Email should be valid", apiResponse.errors.getFirst().message);
                    assertEquals("Email is mandatory", apiResponse.errors.get(1).message);
                });
    }

    @ParameterizedTest
    @ValueSource(strings = { "", " ", "1", "1111111111", "111111111111"} )
    public void whenTryingToCreateAStudentWithoutAPhoneNumberThenReturnBadRequest(String phone) throws Exception {
        var payload = new CreateStudentRequest(
                faker.hitchhikersGuideToTheGalaxy().character(),
                faker.howIMetYourMother().character(),
                faker.internet().emailAddress(),
                phone
        );

        ObjectMapper objectMapper = new ObjectMapper();
        String payloadJson = objectMapper.writeValueAsString(payload);

        mockMvc.perform(MockMvcRequestBuilders.post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    var response = result.getResponse().getContentAsString();
                    var apiResponse = objectMapper.readValue(response,  new TypeReference<ApiResponse<ApiError>>() {});

                    assertEquals(Status.ERROR, apiResponse.status);
                    assertNull(apiResponse.data);
                    assertEquals(1, apiResponse.errors.size());
                    assertEquals("Phone should be 11 digits, the brazilian format", apiResponse.errors.getFirst().message);
                });
    }
}
