package com.usermanagement.controller;

import com.usermanagement.dto.UserSignUpDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthenticationControllerTest {

    TestRestTemplate restTemplate = new TestRestTemplate();

    @LocalServerPort
    private int port;

    @Test
    void givenUser_whenSignIn_thenResponseToken() throws Exception {

        //given
        UserSignUpDTO userSignUpDTO = UserSignUpDTO.builder()
                .username("test")
                .password("12345")
                .build();


        HttpEntity<UserSignUpDTO> entity = new HttpEntity<>(userSignUpDTO, null);

        //when
        ResponseEntity<String> response2 = restTemplate.exchange(
                createURLWithPort("/api/account/sign-up"), HttpMethod.POST, entity, String.class);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/authorize"), HttpMethod.POST, entity, String.class);

        //then
        String expectedToken = response.getBody();
        System.out.println(expectedToken);
        assertTrue(expectedToken.contains("Token:"));
    }

    @Test
    void givenToken_whenSignIn_thenResponseUsername() throws Exception {

        //given
        UserSignUpDTO userSignUpDTO = UserSignUpDTO.builder()
                .username("test24")
                .password("123451")
                .build();


        HttpEntity<UserSignUpDTO> entity = new HttpEntity<>(userSignUpDTO, null);
        HttpHeaders headers = new HttpHeaders();


        //when
        ResponseEntity<String> response2 = restTemplate.exchange(
                createURLWithPort("/api/account/sign-up"), HttpMethod.POST, entity, String.class);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/authorize"), HttpMethod.POST, entity, String.class);


        String token = Objects.requireNonNull(response.getBody()).substring(6);

        headers.add("X-Auth-Token", token);
        HttpEntity<Void> entity2 = new HttpEntity<>(null, headers);

        ResponseEntity<String> response3 = restTemplate.exchange(
                createURLWithPort("/api/account/me"), HttpMethod.GET, entity2, String.class);


        //then
        String actual = response3.getBody();
        String expected = "{\"username\":\"test24\"}";
        JSONAssert.assertEquals(expected, actual, false);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
