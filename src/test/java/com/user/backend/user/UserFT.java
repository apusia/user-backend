package com.user.backend.user;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserFT {

    @LocalServerPort
    private int port;

    @Test
    public void createUser() {
        Faker faker = new Faker();

        UserEntity testUserEntity = UserEntity.builder()
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .build();

        RestAssured
                .given()
//                .body("{ \"email\": \"email-przyklad\", }")
                .body(testUserEntity.toJson())
                .when()
                .post("http://localhost:" + port + "/users")
                .then()
                .statusCode(201);
    }

}
