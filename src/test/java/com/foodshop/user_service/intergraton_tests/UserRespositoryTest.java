package com.foodshop.user_service.intergraton_tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodshop.user_service.UserServiceApplication;
import com.foodshop.user_service.dto.SignUpUserDTO;
import com.foodshop.user_service.models.UserModel;
import com.foodshop.user_service.respositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = UserServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserRespositoryTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * This test do the testing with the production database.
     * Not a good way to handle this testing
     * @throws Exception
     */
    @Test
    void registrationWorksThroughAllLayers() throws Exception {
        SignUpUserDTO user = new SignUpUserDTO();
        user.setEmail("Test@gmail.com");
        user.setPassword("Test@123");
        user.setFirstName("First Name");
        user.setLastName("Last name");
        ResponseEntity<UserModel> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/auth/signup", user, UserModel.class);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

    }
}
