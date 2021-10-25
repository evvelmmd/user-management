package com.usermanagement.controller;

import com.usermanagement.dto.UserSignUpDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    TestRestTemplate restTemplate = new TestRestTemplate();

    @LocalServerPort
    private int port;

    @Test
    void givenNewUser_whenSignUp_thenResponseOK() throws Exception {

        //given
        UserSignUpDTO userSignUpDTO = UserSignUpDTO.builder()
                .username("test")
                .password("123")
                .build();

        HttpEntity<UserSignUpDTO> entity = new HttpEntity<>(userSignUpDTO, null);

        //when
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/account/sign-up"), HttpMethod.POST, entity, String.class);

        //then
        HttpStatus actual = response.getStatusCode();
        assertEquals(HttpStatus.CREATED, actual);
    }

    @Test
    void givenAlreadyExistsUser_whenSignUp_thenResponseError() throws Exception {

        //given
        UserSignUpDTO userSignUpDTO = UserSignUpDTO.builder()
                .username("test")
                .password("123")
                .build();

        HttpEntity<UserSignUpDTO> entity = new HttpEntity<>(userSignUpDTO, null);
        HttpEntity<UserSignUpDTO> entity2 = new HttpEntity<>(userSignUpDTO, null);


        //when
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/account/sign-up"), HttpMethod.POST, entity, String.class);

        ResponseEntity<String> response2 = restTemplate.exchange(
                createURLWithPort("/api/account/sign-up"), HttpMethod.POST, entity2, String.class);

        //then
        String actual = response2.getBody();
        String expected = "{\n" +
                "  \"title\": \"Validasiya xətası.\",\n" +
                "  \"violations\": [\n" +
                "    \"username: Bu istifadəçi adı artıq mövcuddur.\"\n" +
                "  ]\n" +
                "}";
        JSONAssert.assertEquals(expected, actual, false);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
